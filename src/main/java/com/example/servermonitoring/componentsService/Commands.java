package com.example.servermonitoring.componentsService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Commands {
    public OperatingSystem systemInfo(){ // główna funkcja zwracająca dane z innych funkcji
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
                output.add(s);
            }
            p.waitFor();
            p.destroy();
        } catch (Exception e) {}
        return output;
    }
    public Cpu cpu(){
        return new Cpu(cpuName(),cpuUsage()+'%');
    }//funkcja korzystająca z dwóch innych funkcji

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
        String[] all = output
                .stream()
                .filter(s -> s.contains("MemTotal"))
                .collect(Collectors.toList())
                .get(0)
                .split("\\W+");
        String[] available = output
                .stream().filter(s -> s.contains("MemAvailable"))
                .collect(Collectors.toList())
                .get(0)
                .split("\\W+");
        float all2 = Float.parseFloat(all[1]);
        float available2 = Float.parseFloat(available[1]);
        float percentage = (all2-available2)/all2*100;
        all2 = all2/1024/1024;
        String all4 = Float.toString(all2);
        all4 = all4.substring(0,all4.indexOf(".")+2);
        return new Ram(all4.concat(" GB") //całkowita pamięć operacyjna
                ,String.format("%.2f%%",percentage)); //użycie pamięci operacyjnej
    }

    public String uptime(){ //zwraca czas od włączenia systemu
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
        output.remove(0);
        List<Disk> disks = new ArrayList<>();
        for (String s : output){
            disks.add(
                    new Disk(s.split("\\s++")[0] //nazwa
                            ,s.split("\\s++")[1] //pojemność całkowita
                            ,s.split("\\s++")[4])); //wolne miejsce na dysku
        }
        return disks;
    }
}