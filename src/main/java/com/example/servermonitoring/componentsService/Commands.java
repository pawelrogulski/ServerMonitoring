package com.example.servermonitoring.componentsService;

import com.example.servermonitoring.components.*;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Commands {
    public OperatingSystem systemInfo(){ // główna metoda zwracająca dane z innych funkcji
        return new OperatingSystem(hostname(),uptime(),cpu(),ram(),disks(),networkInterfaces());
    }

    public static List<String> getCommandOutput(String command){ //zwraca wynik polecenia w systemie operacyjnym Linux
        String s;
        Process p;
        List<String> output = new ArrayList<>();
        try {
            p = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null) {
                output.add(s); //wynik polecenia jest zapisywany w liście, każda linia wyniku polecenia to jeden element listy
            }
            p.waitFor();
            p.destroy();
        } catch (Exception e) {}
        return output;
    }
    public Cpu cpu(){
        return new Cpu(cpuName(),cpuUsage()+'%');
    }//metoda korzystająca z dwóch podrzędnych metod

    public String cpuName(){ //zwraca nazwe procesora w systemie operacyjnym
        List<String> output4 = getCommandOutput("cat /proc/cpuinfo");
        List<String> nameLine = output4.stream().filter(line -> line.contains("model name")).collect(Collectors.toList());
        String name = nameLine.get(0);
        return name.substring(name.indexOf(":")+2,name.length());
    }
    public String cpuUsage(){ //zwraca poziom użycia procesora w systemie operacyjnym
        List<String> output = getCommandOutput("mpstat");
        String output2 = output.get(3);
        output2 = output2.replaceAll("\\s+","");
        int i = output2.indexOf("all");
        return output2.substring(i+3,i+7);
    }

    public Ram ram(){ //zwraca całkowitą pamięć operacyjną oraz obecne jej wykorzystanie
        List<String> output = getCommandOutput("cat /proc/meminfo");
        String[] totalMemory = output //całkowita pamięć operacyjna
                .stream()
                .filter(s -> s.contains("MemTotal"))
                .collect(Collectors.toList())
                .get(0)
                .split("\\W+");
        String[] availableMemory = output //dostępna pamięć operacyjna
                .stream().filter(s -> s.contains("MemAvailable"))
                .collect(Collectors.toList())
                .get(0)
                .split("\\W+");
        float total = Float.parseFloat(totalMemory[1]);
        float available = Float.parseFloat(availableMemory[1]);
        float percentage = (total-available)/total*100;
        total = total/1024/1024; //całkowita pamięć operacyjna w gigabajtach
        String totalGB = Float.toString(total);
        totalGB = totalGB.substring(0,totalGB.indexOf(".")+2); //całkowita pamięć operacyjna w gigabajtach z dokładnością do 2 miejsca po przecinku
        return new Ram(totalGB.concat(" GB") //całkowita pamięć operacyjna
                ,String.format("%.2f%%",percentage)); //użycie pamięci operacyjnej
    }

    public String uptime(){ //zwraca czas od uruchomienia systemu
        String output = getCommandOutput("uptime")
                .get(0)
                .split("\\s++")[3]
                .replace(":","h ")
                .replace(",","")
                .concat("m");
        return output;
    }
    public String hostname(){
        return getCommandOutput("hostname").get(0);
    } //zwraca nazwę urządzenia
    public List<NetworkInterface> networkInterfaces(){ //zwraca nazwy interfejsów sieciowych i ich obecne użycie
        List<String> output = getCommandOutput("nmcli device status");
        List<NetworkInterface> interfaces = new ArrayList<>();
        for(int i=1;i<output.size();i++){ //zbiera nazwy i stan interfejsów sieciowych
            String[] words = output.get(i).split("\\W+");
            interfaces.add(new NetworkInterface(words[0],words[2],null,null));
        }
        /** Wykorzystanie wielowątkowości w celu zbierania danych o użyciu interfejsów sieciowych
         funkcja MultithreadSpeedTest odczytuje wysłane lub odebrane bajty od uruchomienia systemu,
         następnie czeka sekundę, ponownie odczytuje tą wartość i liczy różnicę pomiędzy nimi.
         Dzięki wykorzystaniu wielowątkowości operacja niezależnie od liczby interfejsów sieciowych
         trwa około sekundy, a nie czas równy dwukrotnośi interfejsów sieciowych wyrażony w sekundach **/
        MultithreadSpeedTest[] tests = new MultithreadSpeedTest[interfaces.size()*2]; // tablica w której będą zbierane wątki
        for(int i=0;i<interfaces.size();i++){
            tests[i*2] = new MultithreadSpeedTest(interfaces.get(i),"rx"); //stworzenie wątku do testowania ruchu przychodzącego
            tests[i*2+1] = new MultithreadSpeedTest(interfaces.get(i),"tx"); //stworzenie wątku do testowania ruchu wychodzącego
            tests[i*2].start(); //uruchomienie wątku do testowania ruchu przychodzącego
            tests[i*2+1].start(); //uruchomienie wątku do testowania ruchu wychodzącego
        }
        for(MultithreadSpeedTest test : tests){ //oczekiwanie na zakończenie działania wszystkich wątków
            try {
                test.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return interfaces;
    }


    public List<Disk> disks(){ //zwraca nazwy, całkowite miejsce oraz zajęte miejsce dysków w systemie
        List<String> output = getCommandOutput("df -x tmpfs -h");
        List<Disk> disks = new ArrayList<>();
        if(output.size()<=1){
            return disks;
        }
        output.remove(0);
        if(output.size()==0){
            return disks;
        }
        for (String s : output){
            disks.add(
                    new Disk(s.split("\\s++")[0] //nazwa
                            ,s.split("\\s++")[1] //pojemność całkowita
                            ,s.split("\\s++")[4])); //wolne miejsce na dysku
        }
        return disks;
    }
}