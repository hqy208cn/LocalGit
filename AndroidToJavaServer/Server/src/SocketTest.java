
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;

public class SocketTest {
 
    private static final int PORT = 7777;
    private List<Socket> mList = new ArrayList<Socket>();
    private ServerSocket server = null;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;
    private String sendMsg;
    private static Logger logger = Logger.getLogger(SocketTest.class); 
    public static void main(String[] args) {
        new SocketTest();
    }
  
    public SocketTest() {
        try {
            server = new ServerSocket(PORT);                         //����һ
            mExecutorService = Executors.newCachedThreadPool();
            System.out.println("������������...");
            
            Socket client = null;
            while (true) {
                client = server.accept();         //�������ÿ���ܵ�һ����Socket�������󣬾ͻ��½�һ��Threadȥ��������֮���ͨ��
                mList.add(client);
                mExecutorService.execute(new Service(client));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("����");
        }
    }
 
    class Service implements Runnable {
        private Socket socket;
        private BufferedReader in = null;
        private PrintWriter printWriter=null;
 
        public Service(Socket socket) {                         //��δ����Ӧ������
            this.socket = socket;
            try {
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter( socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream(),"UTF-8"));
                printWriter.println("�ɹ����ӷ�����"+"�����������ͣ�");
                System.out.println("�ɹ����ӷ�����");
            } catch (IOException e) {
                e.printStackTrace();
            }
 
        }
 
        @Override
        public void run() {
            try {
                while (true) {                                   //ѭ�����ա���ȡ Client �˷��͹�������Ϣ
                    if ((receiveMsg = in.readLine())!=null) {
                        System.out.println("receiveMsg:"+receiveMsg);
                        if (receiveMsg.equals("0")) {
                            System.out.println("�ͻ�������Ͽ�����");
                            printWriter.println("����˶Ͽ�����"+"�����������ͣ�");
                            mList.remove(socket);
                            in.close();
                            socket.close();                         //���� Client �˵ĶϿ��������󣬲��ر� Socket ����
                            break;
                        } else {
                            sendMsg = "���ѽ��գ�" + receiveMsg + "�����������ͣ�";
                            printWriter.println(sendMsg);           //�� Client �˷�����������Ϣ
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}