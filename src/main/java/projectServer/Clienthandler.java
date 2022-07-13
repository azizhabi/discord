package projectServer;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Map.*;
import static java.util.Map.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Clienthandler implements Runnable {
    private String Name;
    private Socket socket;
    private DataInputStream reader;
    private DataOutputStream writer;
    private Accounts account;
    private FileIO file;
    private Group group;
    private massage massages;
    private groupmassage groupmassages;
    private TheServer theserver;
   // private serversockets serversocket;


    private  ArrayList<Accounts> accounts;

    private  ArrayList<Group> groups;

    private  ArrayList<TheServer> server1;

    private  ArrayList<massage> the_massages;

    private  ArrayList<groupmassage> the_groupmassage;

    public Clienthandler(Socket socket) throws IOException {
            this.socket = socket;
            //this.serversocket = new serversockets(socket);
            file = new FileIO();
            this.accounts = new ArrayList<>();
            accounts = file.readAccount();
            this.groups = new ArrayList<>();
            groups = file.readGroups();
            this.server1 = new ArrayList<>();
            server1 = file.readServer();
            this.the_massages = new ArrayList<>();
            the_massages = file.read_massage();
            this.the_groupmassage = new ArrayList<>();
            the_groupmassage = file.read_groupmassage();
            try{ 
//            reader = new ObjectInputStream(socket.getInputStream());
//            writer = new ObjectOutputStream(socket.getOutputStream());
             this.reader = new DataInputStream(socket.getInputStream());
             this.writer = new DataOutputStream(socket.getOutputStream());
       }catch(IOException e) {
           e.printStackTrace();
           close();
       }
        
    }
    private void close(){
        try{
        if (reader!= null) {
            reader.close();
        }
        if (writer != null) {
            writer.close();
        }
        if (socket != null) {
            socket.close();
        }
        
        }catch (IOException e) {
            e.printStackTrace();
        }   
       
    }
    @Override
    public void run() {
        String massage;
        int i = 0;
        while(i == 0) {
        try{
        massage = (String) reader.readUTF();
        JSONObject json = new JSONObject(massage);
        switch(json.getString("methode")) {
            case "CheckUsername":
                checkusername(json);
                break;
            case "CreatAccount" :
                CreatAccount(json);
                break;
            case  "UnBlock" :
                unblock(json);
                break;
            case "SendMassage" :
                SendMassage(json);
                break;
            case "GroupMassage" :
                groupMassage(json);
                break;
            case "CreatGroup" :
                creatGroup(json);
                break;
            case "FriendRequest" :

                FriendRequest(json);
                break; 
            case "CheckUser" :
                CheckUser(json);
                break;
            case "CreatServer" :
                creatServer(json);
                break;
            case "AddRole" :
                addRole(json);
                break;
            case "FriendList" :
                friendList(json);
                break;
            case "OnlineList" :
                onlinelist(json);
                break;
            case "BlockList" :
                blocklist(json);
                break;
            case "AnswerRequest" :
                answerRequest(json);
                break;
            case "DeleteRequest" :
                deleterequest(json);
                break;
            case "ShowRequest" :
                showrequest(json);
                break;
            case "getmassage" :
                getmassage(json);
                break;
            case "MyGroups" :
                mygroup(json);
                break;
            case "Showgroupmassage" :
                showgroupmassage(json);
                break;
            case "CheckServerUser" :
                checkserveruser(json);
                break;
            case "UserSatuation" :
                usersatuation(json);
                break;
            case "ShowServerMember" :
                showservermember(json);
                break;
            case "Server" :
                server(json);
                break;
            case "getGroupMassage" :
                getgroupmassage(json);
                break;
            case "ShowGroupMember" :
                showgroupmember(json);
                break;
            case "ShowProfile" :
                showprofile(json);
                break;
        }
        
        }catch(IOException e){
            e.printStackTrace();
        }
        }
    }

    private void unblock(JSONObject json) {
    for (Accounts account : file.readAccount()) {
        if (json.getString("Username").equals(account.getName())) {
            account.getBlocklist().remove(account.getName());
            account.setFriends(account.getName());
            accounts.add(account);
            this.file.writeAccounts(accounts);
        }
    }

    }

    private void deleterequest(JSONObject json) {
        for (Accounts account : file.readAccount()) {
            if (json.getString("Username").equals(account.getName())) {
                System.out.println("it is in accoutn");
                for (String str : account.getRequest()) {
                    System.out.println("it is in requests");
                    if (json.getString("DeleteName").equals(str)) {

                        account.getRequest().remove(str);
                        System.out.println("it deleted");
                        accounts.add(account);
                        this.file.writeAccounts(accounts);
                    }
                }
            }
        }
    }

    private void blocklist(JSONObject json) {
        JSONObject json1 = new JSONObject();
        int i = 1;
        JSONArray jsonArray = new JSONArray();
        json1.put("methode","BlockList");
        for (Accounts account : file.readAccount()) {
            if (json. getString("Username").equals(account.getName())) {
                for (String block : account.getBlocklist()) {
                    for (Accounts acc : file.readAccount()) {
                        if (acc.getName().equals(block)){
                           jsonArray.put(block);
                        }

                    }
                }

                break;
            }
        }
        json1.put("Username",jsonArray);
        this.Sendprivate(json1);
    }

    private void onlinelist(JSONObject json) {
        JSONArray json3 = new JSONArray();
        for (Accounts account : file.readAccount()) {
            if (json.getString("Username").equals(account.getName())) {
                for (String online : account.getFriends()) {
                    for (Accounts acc : file.readAccount()) {
                        if (acc.getName().equals(online)&&acc.getState().equals("Online")) {
                            json3.put(online);
                        }
                    }
                }
            }
        }


        JSONObject json2 = new JSONObject();
        json2.put("methode","OnlineList");
        json2.put("Username",json3);
        this.Sendprivate(json2);
    }

    private void checkusername(JSONObject json) {
        for (Accounts account: file.readAccount()) {
            if (json.getString("Username").equals(account.getName())) {
                JSONObject json1 = new JSONObject();
                json1.put("methode","CheckUsername");
                json1.put("Answer","False");
                this.Sendprivate(json1);
                return;
            }
        }

        JSONObject json1 = new JSONObject();
        json1.put("methode","CheckUsername");
        json1.put("Answer","True");
        this.Sendprivate(json1);

    }

    private void friendList(JSONObject json) {
        int i = 1;
        JSONObject json1 = new JSONObject();
        json1.put("methode", "FriendList");
     JSONArray jsonArray = new JSONArray();
        for (Accounts account: file.readAccount()) {
            if (json.getString("Username").equals(account.getName())) {
                System.out.println("the size of in server:" + account.getFriends().size());
                for (String a: account.getFriends()){
                    System.out.println(a);
                    jsonArray.put(a);
                }
            }
        }
       json1.put("Username",jsonArray);
        this.Sendprivate(json1);
    }
    private void CreatAccount(JSONObject json) {
        Name = json.getString("Username");
        String password = json.getString("password");
        String PhoneNumber = json.getString("phoneNumber");
        String Email = json.getString("Email");
        account = new Accounts(Name,json.getString("password"),json.getString("phoneNumber"),json.getString("Email"));
        account.setState(json.getString("State"));
        accounts.add(account);
        file.writeAccounts(accounts);
        JSONObject json1 = new JSONObject();
        json1.put("methode", "CreatAccount");
        json1.put("Answer", "True");
        Sendprivate(json1);
        
    }
    private void getmassage (JSONObject json) {
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        for (massage mas : file.read_massage()) {
            if (json.getString("Username").equals(mas.getName()) && json.getString("FriendName").equals(mas.getSended()) || json.getString("FriendName").equals(mas.getName()) && json.getString("Username").equals(mas.getSended())) {
                    jsonArray.put(mas.getMassages());
                    jsonArray1.put(mas.getSended());

                }
            }

        JSONObject json1 = new JSONObject();
        json1.put("methode", "getmassage");
        json1.put("Username", jsonArray1);
        json1.put("massage", jsonArray);
        this.Sendprivate(json1);
        }
    private void SendMassage(JSONObject json) {
        
               for (Accounts account: file.readAccount()) {
                if (json.getString("FriendName").equals(account.getName())){
                    for (String block: account.getBlocklist()){
                    if (json.getString("Username").equals(block)){
                        JSONObject json1 = new JSONObject();
                        json1.put("methode", "privateMassage");
                        json1.put("Username", account.getName());
                        json1.put("Answer","False");
                        try{
                            this.writer.writeUTF(json1.toString());
                            return;
                        }catch(IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    }
                   
                            massages =  new massage(json.getString("Username"));
                            massages.setMassages(json.getString("massage"));
                            massages.setSended(json.getString("FriendName"));
                            the_massages.add(massages);
                            this.file.write_massage(the_massages);
                            JSONObject json1 = new JSONObject();
                            json1.put("methode", "privateMassage");
                            json1.put("Answer", "True");
                            this.Sendprivate(json1);
                            break;
                        
               }
            }
        }
    
    private void Sendprivate(JSONObject json1) {
          try{
            this.writer.writeUTF(json1.toString());
            return;
            }catch (IOException e) {
              e.printStackTrace();
              return;
            }
    }    
    private void groupMassage(JSONObject json) throws IOException {
        int flag = 0;
        for (Group a : file.readGroups()) {
                if (json.getString("GroupName").equals(a.getName())) {
                    for (String name : a.getMembers()) {
                    
                            for (Accounts as : file.readAccount()){
                             if (as.getName().equals(name)) {
                                    groupmassages = new groupmassage(json.getString("GroupName"),json.getString("Username"),json.getString("massage"));
                                    the_groupmassage.add(groupmassages);
                                    this.file.write_groupmassage(the_groupmassage);
                                    flag = 1;
                                    break;
                                    }
                                
                                }
                            }
                        }
                    }
                
        if (flag == 1) {
            JSONObject json1 = new JSONObject();
            json1.put("methode", "GroupMassage");
            json1.put("Answer", "True");
            this.Sendprivate(json1);
            
        }
        else {
            JSONObject json1 = new JSONObject();
            json1.put("methode", "GroupMassage");
            json1.put("Answer", "False");
            this.Sendprivate(json1);
        }
        
    }
    private void FriendRequest(JSONObject json) {
        int flag = 0;
        for (Accounts a : file.readAccount()) {
            if (json.getString("FriendName").equals(a.getName())) {
                a.setRequest(json.getString("Username"));
                accounts.add(a);
                this.file.writeAccounts(accounts);
                JSONObject json1 = new JSONObject();
                json1.put("methode", "FriendRequest");
                json1.put("Answer", "True");
               this.Sendprivate(json1);
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            JSONObject json1 = new JSONObject();
             json1.put("methode", "FriendRequest");
             json1.put("Answer", "False");
            // this.Sendprivate(json1);
        
        }
        
    }
    private void CheckUser(JSONObject json) {
        JSONObject json1 = new JSONObject();
        for (Accounts a : file.readAccount()) {
            System.out.println("your user:"+ json.getString("Username"));
            System.out.println("Your pass:" +json.getString("password"));
            if (json.getString("Username").equals(a.getName())&&json.getString("password").equals(a.getPassword())) {
                json1.put("methode", "CheckUser");
                json1.put("Answer", "True");
                Sendprivate(json1);
                return;
            }
        }

           json1.put("methode", "CheckUser");
           json1.put("Answer", "False");
           Sendprivate(json1);
           return;
                
    }
    private void creatGroup(JSONObject json) {
        JSONObject json1 = new JSONObject();
            for (TheServer a : file.readServer() ) {
                if (json.getString("ServerName").equals(a.getServerName())) {
                        group = new Group(json.getString("GroupName"),json.getString("GroupCreaters"));
                        a.setGroups(group);
                        groups.add(group);
                        server1.add(a);
                        this.file.writeServer(server1);
                        this.file.writeGroups(groups);
                        json1.put("methode", "CreatGroup");
                        json1.put("Answer", "True");
                        Sendprivate(json1);
                        return;
                        }
                }
            
        json1.put("methode", "CreatGroup");
        json1.put("Answer", "False");
        this.Sendprivate(json1);      
        return;    
    }
    private void creatServer(JSONObject json) {
        theserver = new TheServer(json.getString("ServerName"),json.getString("ServerCreater"));
        server1.add(theserver);
        this.file.writeServer(server1);
        JSONObject json1 = new JSONObject();
        json1.put("methode", "CreatServer");
        json1.put("Answer", "True");
        this.Sendprivate(json1);
        return;
    }   
    private void addRole(JSONObject json) {
        JSONObject json1 = new JSONObject();
        for (Accounts s : file.readAccount()) {
            if (json.getString("UserName").equals(s.getName())){
              for (TheServer a : file.readServer()) {
                   if (a.getServerName().equals("ServerName")){
                     switch (json.getString("Role")) {
                          case "GroupCreater":
                              a.setGroupCreater(json.getString("UserName"));
                              server1.add(a);
                              file.writeServer(server1);
                              json1.put("methode", "AddRole");
                              json1.put("Answer", "True");
                              Sendprivate(json1);
                              return;
                           case "GroupDeleter" :
                              a.setGroupDeleter(json.getString("UserName"));
                              server1.add(a);
                              file.writeServer(server1);
                              
                              json1.put("methode", "AddRole");
                              json1.put("Answer", "True");
                              Sendprivate(json1);
                              return;
                          case "UserDeleter" :
                              a.setUserDeleter(json.getString("UserName"));
                              server1.add(a);
                              file.writeServer(server1);
                              
                              json1.put("methode", "AddRole");
                              json1.put("Answer", "True");
                              Sendprivate(json1);
                              return;
                          case "MassagePiner" :
                              a.setMassagepiner(json.getString("UserName"));
                              server1.add(a);
                              file.writeServer(server1);
                              json1.put("methode", "AddRole");
                              json1.put("Answer", "True");
                              Sendprivate(json1);
                              return;
                        }
                   }
                }
            }
        }
        json1.put("methode", "AddRole");
        json1.put("Answer", "False");
        Sendprivate(json1);
    }
    private void showrequest (JSONObject json) {
        JSONObject json1 = new JSONObject();
        json1.put("methode", "ShowRequest");
        JSONArray jsonArray = new JSONArray();
        int i = 1;
        for (Accounts s : file.readAccount()) {
         if (json.getString("Username").equals(s.getName())) {
            for (String a : s.getRequest()) {
                jsonArray.put(a);
                }
            }
         }
        json1.put("Username",jsonArray);
        this.Sendprivate(json1);
    }
    private void answerRequest(JSONObject json) {

            for (Accounts account: file.readAccount()) {
                if (json.getString("Username").equals(account.getName())) {
                    account.setFriends(json.getString("FriendName"));
                    accounts.add(account);

                    file.writeAccounts(accounts);
                }
                if (json.getString("FriendName").equals(account.getName())) {
                    account.setFriends(json.getString("Username"));
                    accounts.add(account);
                    file.writeAccounts(accounts);
                }
            }
            JSONObject json1 = new JSONObject();
            json1.put("methode", "AnswerRequest");
            json1.put("Answer", "True");
            //this.Sendprivate(json1);

    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    private void mygroup(JSONObject json) {
        for (Group group: file.readGroups()) {
            for (String member : group.getMembers()) {
                if (json.getString("Username").equals(member)) {
                    JSONObject json1 = new JSONObject();
                    json1.put("methode", "MyGroups");
                    json1.put("GroupName", group.getName());
                    this.Sendprivate(json1);
                }
                
            }
            
        }
    }
    private void showgroupmassage(JSONObject json) {
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        JSONObject json1 = new JSONObject();
        json1.put("methode", "showgroupmassages");
        for (groupmassage massages: file.read_groupmassage()) {
            if (json.getString("GroupName").equals(massages.getGroupName())) {
                jsonArray.put(massages.getName());
                jsonArray1.put(massages.getName());

            }
            
        }

        json1.put("Sended", jsonArray);
        json1.put("massage", jsonArray1);
        this.Sendprivate(json1);
    }
    private void checkserveruser(JSONObject json) {
        for (TheServer server: file.readServer()) {
            if (json.getString("ServerName").equals(server.getServerName())) {
                for (String serveruser : server.getMembers()) {
                    if (json.getString("ServerUser").equals(serveruser)) {
                        JSONObject json1 = new JSONObject();
                        json1.put("methode", "CheckServerUser");
                        json1.put("Answer", "True");
                        this.Sendprivate(json1);
                        return;
                    }
                }
                
            }
        }
        JSONObject json1 = new JSONObject();
        json1.put("methode", "CheckServerUser");
        json1.put("Answer", "False");
        this.Sendprivate(json1);
    }
    private void usersatuation(JSONObject json) {
        for (TheServer server: file.readServer()) {
            if (json.getString("ServerName").equals(server.getServerName())){
              for (String admin: server.getAdmins()) {
                 if (json.getString("Username").equals(admin)) {
                     JSONObject json1 = new JSONObject();
                     json1.put("methode", "UserSatuation");
                     json1.put("ability", "Admins");
                     this.Sendprivate(json1);
                      return;
                 }
             }

             for (String deleter: server.getUserDeleter()) {
                 if (json.getString("Username").equals(deleter)) {
                     JSONObject json1 = new JSONObject();
                     json1.put("methode", "UserSatuation");
                     json1.put("ability", "UserDeleter");
                     this.Sendprivate(json1);
                      return;
                 }
             }

             for (String groupcreater: server.getGroupCreater()) {
                 if (json.getString("Username").equals(groupcreater)) {
                     JSONObject json1 = new JSONObject();
                     json1.put("methode", "UserSatuation");
                     json1.put("ability", "groupcreater");
                     this.Sendprivate(json1);
                      return;
                 }
             }

             for (String massagepiner: server.getMassagepiner()) {
                 if (json.getString("Username").equals(massagepiner)) {
                     JSONObject json1 = new JSONObject();
                     json1.put("methode", "UserSatuation");
                     json1.put("ability", "massagepiner");
                     this.Sendprivate(json1);
                      return;
                 }
             }

             for (String groupdeleter: server.getGroupDeleter()) {
                 if (json.getString("Username").equals(groupdeleter)) {
                     JSONObject json1 = new JSONObject();
                     json1.put("methode", "UserSatuation");
                     json1.put("ability", "groupdeleter");
                     this.Sendprivate(json1);
                      return;
                 }
             }

        }
    }
       JSONObject json1 = new JSONObject();
       json1.put("methode", "UserSatuation");
       json1.put("ability", "OnlyMember");
       this.Sendprivate(json1);
  }
    private void showservermember(JSONObject json) {
        JSONArray jsonArray = new JSONArray();
        JSONObject json1 = new JSONObject();
        json1.put("methode", "ShowServerMember");
        for (TheServer server: file.readServer()) {
            if (json.getString("ServerName").equals(server.getServerName())) {
                for (String member: server.getMembers()) {
                    jsonArray.put(member);

                }
                
                break;
            }
        }
        json1.put("MemberName", jsonArray);
        this.Sendprivate(json1);
    }
    private void server(JSONObject json) {
        switch (json.getString("job")) {
            case "ShowGroups" :
                JSONArray jsonArray = new JSONArray();
               for (TheServer server : file.readServer()) {
                    if (json.getString("ServerName").equals(server.getServerName())) {

                        for (Group group : server.getGroups()) {
                            for (String user_name: group.getMembers()) {
                                if (json.getString("Username").equals(user_name)) {
                                    jsonArray.put(group.getName());

                                    
                                }
                            }
                        }

                    }
                }
                JSONObject json1 = new JSONObject();
                json1.put("methode", "Server");
                json1.put("GroupName", jsonArray);
                this.Sendprivate(json1);
                break;
            case "UserDeleter" :
                JSONArray jsonArray1 = new JSONArray();
                for (TheServer server : file.readServer()) {
                    if (json.getString("ServerName").equals(server.getServerName())) {
                        for (String member : server.getMembers()) {
                            if (json.getString("DeletedName").equals(member)) {
                                server.getMembers().remove(member);
                                JSONObject json2 = new JSONObject();
                                json2.put("methode", "Server");
                                json2.put("Job", "USerDeleter");
                                json2.put("Answer", "True");
                                        this.Sendprivate(json2);
                                        return;
                            }
                        }
                    }
                }
                                JSONObject json2 = new JSONObject();
                                json2.put("methode", "Server");
                                json2.put("Job", "USerDeleter");
                                json2.put("Answer", "False");
                                this.Sendprivate(json2);
                break;
            case "Massagepiner" :
                   for (TheServer server : file.readServer()) {
                       if (json.getString("ServerName").equals(server.getServerName())) {
                           for (Group group : server.getGroups()) {
                               if (json.getString("GroupName").equals(group.getName())) {
                                   group.setPinmassages(json.getString("pinMassage"));
                                   JSONObject json4 = new JSONObject();
                                   json4.put("methode", "Server");
                                   json4.put("Job", "MassagePiner");
                                   json4.put("Answer", "True");
                                   this.Sendprivate(json4);
                                   return;
                                }
                           }
                       }
                   }
                   JSONObject json4 = new JSONObject();
                    json4.put("methode", "Server");
                    json4.put("Answer", "False");
                json4.put("Job", "MassagePiner");
                this.Sendprivate(json4);
                break;
            case "GroupDeleter" :
                for (TheServer server : file.readServer()) {
                    if (json.getString("ServerName").equals(server.getServerName())) {
                        for (Group group : server.getGroups()) {
                            if (json.getString("GroupName").equals(group.getName())) {
                                server.getGroups().remove(group.getName());
                                groups.remove(group.getName());
                                JSONObject json3 = new JSONObject();
                                json3.put("methode", "Server");
                                json3.put("Job","GroupDeleter");
                                json3.put("Answer", "True");
                                this.Sendprivate(json3);
                            }

                        }
                    }
                }
                                JSONObject json5 = new JSONObject();
                                json5.put("methode", "Server");
                                json5.put("Job","GroupDeleter");
                                json5.put("Answer", "False");
                                this.Sendprivate(json5);
                break;
                
            default:
                JSONObject json6 = new JSONObject();
                json6.put("methode", "Server");
                json6.put("Job","Onlymember");
                json6.put("Answer", "True");
                this.Sendprivate(json6);
                
                break;
        }
    }
    private void getgroupmassage(JSONObject json) {
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        for (groupmassage as : file.read_groupmassage()) {
            if (json.getString("GroupName").equals(as.getGroupName())) {

                jsonArray.put(as.getName());
                jsonArray1.put(as.getMassage());
            }
        }
        JSONObject json1 = new JSONObject();
        json1.put("methode", "getGroupMassage");
        json1.put("massage", jsonArray1);
        json1.put("Sended", jsonArray);
        this.Sendprivate(json1);


    }
    private void showgroupmember(JSONObject json) {
        JSONArray jsonArray = new JSONArray();
        for (Group a : file.readGroups()) {
            if (json.getString("GroupName").equals(a.getName())){
            for (String str : a.getMembers()) {
                jsonArray.put(str);            }
            }
        }

        JSONObject json1 = new JSONObject();
        json1.put("methode", "ShowGroupMember");
        json1.put("member", jsonArray);
        this.Sendprivate(json1);

    }
    private void showprofile(JSONObject json) {
        for (Accounts account : file.readAccount()) {
            if (json.getString("Username").equals(account.getName())) {
                JSONObject json1 = new JSONObject();
                json1.put("Username", account.getName());
                json1.put("PhoneNumber", account.getPhoneNumber());
                json1.put("EmailAddress", account.getEmail());
                json1.put("State", account.getState());
                this.Sendprivate(json1);
            }
        }
    }

}
