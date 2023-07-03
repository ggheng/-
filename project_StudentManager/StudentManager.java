package studentmanager.project_StudentManager;

import java.io.*;

import java.util.*;

public class StudentManager {


    public static void main(String[] args) throws IOException {

        ArrayList<Student> array = new ArrayList<>();

        //在文件中读取学生资料
        readStuInfo(array);
        //System.out.println(array);

        while (true) {
            System.out.println("-----欢迎来到学生管理系统-----");
            System.out.println("1 添加学生");
            System.out.println("2 删除学生");
            System.out.println("3 修改学生");
            System.out.println("4 查看所有学生");
            System.out.println("5 退出");
            System.out.println("请输入你的选择：");



            Scanner sc = new Scanner(System.in);
            String num = sc.nextLine();

            switch (num) {
                case "1":
//				System.out.println("添加学生");
                    addStudent(array);
                    break;
                case "2":
//				System.out.println("删除学生");
                    deleteStudent(array);
                    break;
                case "3":
//				System.out.println("修改学生");
                    updateStudent(array);
                    break;
                case "4":
//				System.out.println("查看所有学生");
                    findAllStudent(array);
                    break;
                case "5":
                    System.out.println("谢谢使用");
                    // break;
                    System.exit(0);// JVM虚拟机退出
            }
        }

    }

    private static void readStuInfo(ArrayList<Student> array) throws IOException {

        //利用BufferedReader流来读取文件中的数据
        //InputStreamReader转换流
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("C:\\Users\\admin\\IdeaProjects\\studentManager\\src\\studentInfo.txt"), "UTF-8"));

        String line;
        System.out.println("学生管理系统中已经保存的学生数据为：");
        while ((line = br.readLine()) != null) {
            //打印每一行数据 (即每一个学生的信息）
            System.out.println(line);

            String[] stuInfoArr=line.split(",");

            String[] arr1=stuInfoArr[0].split("=");
            String[] arr2=stuInfoArr[1].split("=");
            String[] arr3=stuInfoArr[2].split("=");
            String[] arr4=stuInfoArr[3].split("=");

            Student stu=new Student(arr1[1],arr2[1],arr3[1],arr4[1]);
            array.add(stu);
        }

    }


    public static void addStudent(ArrayList<Student> array) throws IOException {

        Scanner sc = new Scanner(System.in);

        String sid;
        while (true) {
            System.out.println("请输入学生学号：");
            sid = sc.nextLine();
            if (isUsed(array,sid) == true) {
                System.out.println("该学号已被输入过，请重新输入学号");//学号作为唯一的认证标志
            } else {
                break;
            }
        }

        System.out.println("请输入姓名：");
        String name = sc.nextLine();

        System.out.println("请输入学生年龄：");
        String age = sc.nextLine();

        System.out.println("请输入学生住址：");
        String address = sc.nextLine();

        Student s = new Student(sid,name,age,address);
//        s.setSid(sid);
//        s.setName(name);
//        s.setAge(age);
//        s.setAddress(address);

        array.add(s);

        //创建字符打印流的对象
        //要续写，因为还要保存文件中已有的数据
        //利用PrintWriter来将集合中的数据写入文件当中
        PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\admin\\IdeaProjects\\studentManager\\src\\studentInfo.txt",true), true);
        pw.println(s);
        pw.close();

        System.out.println("添加对象成功");

    }

    //用以判断学生的学号是否已经添加至文件中
    public static boolean isUsed( ArrayList<Student> array,String sid) {

        for (Student student : array) {
            if(student.getSid().equals(sid)){
                return true;
            }
        }

        return false;
    }

    public static void findAllStudent(ArrayList<Student> array) {

        if (array.size() == 0) {
            System.out.println("无信息，请先添加信息再查看");
            return;
        }
        System.out.println("学号\t姓名\t年龄\t居住地");
        for (int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            System.out.println(s.getSid() + "\t" + s.getName() + "\t" + s.getAge() + "岁\t" + s.getAddress());

        }

    }

    public static void deleteStudent(ArrayList<Student> array) throws IOException {

        boolean flag = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的学生学号");
        String sid = sc.nextLine();

        for (int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            if (s.getSid().equals(sid)) {
                array.remove(i);
                //同时要移除文件中的对应学号的那条记录
                //这时候集合中的元素已经完成了更新
                //创建字符打印流的对象
                //这时候第一次不需要续写，重新把集合中的数据添加到文件当中即可
                changedArraytoFile(array);

                flag = true;
                break;
            }
        }

        if (flag == true) {
            System.out.println("删除学生信息成功");
        } else {
            System.out.println("无该学生信息，请核对后重试");
        }

    }

    public static void updateStudent(ArrayList<Student> array) throws IOException {

        boolean flag = false;

        System.out.println("请输入要修改学生的学号");
        Scanner sc = new Scanner(System.in);
        String sid = sc.nextLine();

        for (int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            if (s.getSid().equals(sid)) {
                flag = true;
                break;
            }
        }

        if (flag == false) {
            System.out.println("输入修改学生的学号有误，请重新检查后输入");
            return;
        }

        System.out.println("请输入要修改学生的姓名：");
        String name = sc.nextLine();
        System.out.println("请输入要修改学生的年龄：");
        String age = sc.nextLine();
        System.out.println("请输入要修改学生的地址：");
        String address = sc.nextLine();

        Student s = new Student(sid,name,age,address);
//        s.setSid(sid);
//        s.setName(name);
//        s.setAge(age);
//        s.setAddress(address);

        for (int i = 0; i < array.size(); i++) {
            Student stu = array.get(i);
            //遍历找出要修改的学生的位置
            if (stu.getSid().equals(sid)) {
                array.set(i, s);//用s修改i位置的元素
                break;
            }
        }

        //此时array已经被修改，要重新将array中的信息传至文件中
        changedArraytoFile(array);

        System.out.println("修改学生信息成功");
    }

    //集合中的元素被修改后重新写入文件当中去的方法
    private static void changedArraytoFile(ArrayList<Student> array) throws IOException {
        int temp1=0;
        for (Student student : array) {
            if (temp1==0){
                PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\admin\\IdeaProjects\\studentManager\\src\\studentInfo.txt",false), true);
                pw.println(student);
                pw.close();
                temp1=1;//第一次续写完成后后面就应该开启续写
            }else{
                PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\admin\\IdeaProjects\\studentManager\\src\\studentInfo.txt",true), true);
                pw.println(student);
                pw.close();
            }
        }
    }
}


