
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class ServerRunnable extends Thread{

    private Socket socket;
    ////////////////////////////////

    ////////////////////////////////
    public ServerRunnable(Socket socket){
        this.socket = socket;
    }

    public void run(){

        try{

            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", true));
            BufferedReader br = new BufferedReader(new FileReader("output.txt"));

            PackageData pd = (PackageData) inStream.readObject();

            String s = "";

            if(pd.operationType.equals("ADD")){
                Student serverList = new Student(null, pd.student.name, pd.student.surname, pd.student.age);
                s+=serverList.name+" "+serverList.surname+" "+serverList.age+"\n";
                System.out.println(s);
                bw.write(s);
            }
            else if(pd.operationType.equals("LIST")){
                ArrayList<Student> st = new ArrayList<Student>();
                String sFromDatabase="";
                while((sFromDatabase = br.readLine())!=null){
                    String[] t = sFromDatabase.split(" ");
                    Student dataForClient = new Student(null, t[0], t[1], Integer.parseInt(t[2]));
                    st.add(dataForClient);
                }
                PackageData finalDataToClient = new PackageData(st);
                outStream.writeObject(finalDataToClient);

            }
            bw.close();
            br.close();
            outStream.close();
            inStream.close();
            socket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}

