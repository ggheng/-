package studentmanager.project_StudentManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

public class Ui extends JFrame implements MouseListener {

    ArrayList<Student> array = new ArrayList<>();

    //按钮
    JButton jButton1 = new JButton("增加学生");
    JButton jButton2 = new JButton("删除学生");
    JButton jButton3 = new JButton("修改学生");
    JButton jButton4 = new JButton("查询学生");
    JButton jButton5 = new JButton("退出系统");

    //中间文本区
    //JTextArea jTextArea=new JTextArea();

    //添加学生的组件 ：创建学号 姓名 年龄 地址的输入框
    JTextField sidText = new JTextField();
    JTextField nameText = new JTextField();
    JTextField ageText = new JTextField();
    JTextField addressText = new JTextField();
    //创建添加学生对应的提示信息
    JLabel sidText_ = new JLabel("请输入学生学号：");
    JLabel nameText_ = new JLabel("请输入学生姓名：");
    JLabel ageText_ = new JLabel("请输入学生年龄：");
    JLabel addressText_ = new JLabel("请输入学生住址：");
    //这是添加学生的确认按钮
    JButton jButton6 = new JButton("确认_添加");//确认添加按钮

    //删除学生的组件 ：
    JTextField getSidText = new JTextField();
    JLabel getSidText_ = new JLabel("请输入要删除的学生学号: ");
    //这是删除学生的确认按钮
    JButton jButton7 = new JButton("确认_删除");//确认删除按钮

    //修改学生的组件 ：
    JTextField sidText1 = new JTextField();
    JTextField nameText1 = new JTextField();
    JTextField ageText1 = new JTextField();
    JTextField addressText1 = new JTextField();
    //创建对应的提示信息
    JLabel sidText1_ = new JLabel("请输入要修改的学生学号：");
    JLabel nameText1_ = new JLabel("请输入要修改的学生姓名：");
    JLabel ageText1_ = new JLabel("请输入要修改的学生年龄：");
    JLabel addressText1_ = new JLabel("请输入要修改的学生住址：");
    JButton jButton8 = new JButton("确认_修改");//确认修改按钮

    //查询学生的组件
    // 表格模型
    private final DefaultTableModel tableModel = new DefaultTableModel();
    JTable table = new JTable(tableModel);
    JPanel panel = new JPanel();


    public Ui() throws IOException {

        //读取学生数据
        readStuInfo(array);

        //初始化界面
        initFrame();

        //在这个界面中添加内容
        initView();

        //让当前界面显示出来
        this.setVisible(true);
    }


    //在这个界面中添加内容
    private void initView() {


        //添加照片
        JLabel picture = new JLabel(new ImageIcon("C:\\Users\\admin\\IdeaProjects\\studentManager\\src\\data\\学生管理系统照片.png"));
        picture.setBounds(0, 0, 580, 110);
        add(picture);

        JLabel welcomeWord = new JLabel("欢迎来到学生管理系统，你可点击下面五个按钮进行操作!");
        welcomeWord.setBounds(150, 115, 310, 40);
        add(welcomeWord);

        //添加四个按钮
        jButton1.setBounds(65, 450, 90, 40);
        jButton2.setBounds(195, 450, 90, 40);
        jButton3.setBounds(325, 450, 90, 40);
        jButton4.setBounds(455, 450, 90, 40);
        jButton5.setBounds(260, 500, 90, 40);

        //给五个按钮绑定鼠标事件
        jButton1.addMouseListener(this);
        jButton2.addMouseListener(this);
        jButton3.addMouseListener(this);
        jButton4.addMouseListener(this);
        jButton5.addMouseListener(this);
        jButton6.addMouseListener(this);
        jButton7.addMouseListener(this);
        jButton8.addMouseListener(this);

        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);
        add(jButton5);

    }


    //初始化界面
    private void initFrame() {
        //进行界面的数据初始化
        this.setSize(585, 600);

        //设置界面的标题
        this.setTitle("学生管理系统");

        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);

        //设置关闭模式 有四种不同的模式，对应着关闭的不同的四个操作
        //0 无任何操作  1 默认模式  2 关闭最后一个界面关虚拟机（所有界面均要做该种模式）
        //3 关闭一个界面java虚拟机关闭  3=WindowConstants.EXIT_ON_CLOSE
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //取消内部默认布局
        this.setLayout(null);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

//        try {
//            readStuInfo(array);
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }

        if (e.getSource() == jButton1) {

            System.out.println("用户点击了添加学生按钮");

            //要把其他按钮所隐藏的组件重新开起来
            sidText.setVisible(true);
            sidText_.setVisible(true);
            nameText.setVisible(true);
            nameText_.setVisible(true);
            ageText.setVisible(true);
            ageText_.setVisible(true);
            addressText.setVisible(true);
            addressText_.setVisible(true);
            jButton6.setVisible(true);
            //把删除学生的按钮的组件关掉
            jButton7.setVisible(false);
            getSidText.setVisible(false);
            getSidText_.setVisible(false);
            //把修改学生的组件关掉
            sidText1.setVisible(false);
            nameText1.setVisible(false);
            ageText1.setVisible(false);

            addressText1.setVisible(false);
            sidText1_.setVisible(false);
            nameText1_.setVisible(false);
            ageText1_.setVisible(false);
            addressText1_.setVisible(false);
            jButton8.setVisible(false);
            //关闭查询学生组件
            panel.setVisible(false);

            //当点击了添加学生按钮后会出现确认按钮
            jButton6.setBounds(240, 360, 90, 40);
            add(jButton6);

            //展示要输入信息的提示和输入框
            sidText_.setBounds(100, 150, 100, 50);
            nameText_.setBounds(100, 200, 100, 50);
            ageText_.setBounds(100, 250, 100, 50);
            addressText_.setBounds(100, 300, 100, 50);
            add(sidText_);
            add(nameText_);
            add(ageText_);
            add(addressText_);
            //输入框
            sidText.setBounds(200, 150, 200, 35);
            nameText.setBounds(200, 200, 200, 35);
            ageText.setBounds(200, 250, 200, 35);
            addressText.setBounds(200, 300, 200, 35);
            add(sidText);
            add(nameText);
            add(ageText);
            add(addressText);

        } else if (e.getSource() == jButton6) {
            //点击了确认按钮时代表添加学生的信息已经输入完成，添加文件中

            boolean flag = true;

            System.out.println("用户点击了确认添加按钮");
            String sid = sidText.getText();

            //学号作为学生的唯一认证，每次增加的学生学号不能与之前学生重复
            if (isUsed(array, sid) == true) {
                showJDialog("该学号已被输入过，请输入新的学号");
                flag = false;
            }

            if (flag == false) {
                return;
            }


            String name = nameText.getText();
            String age = ageText.getText();
            String address = addressText.getText();
            Student s = new Student(sid, name, age, address);
            array.add(s);

            //创建字符打印流的对象
            //要续写，因为还要保存文件中已有的数据
            //利用PrintWriter来将集合中的数据写入文件当中
            PrintWriter pw;
            try {
                pw = new PrintWriter(new FileWriter("C:\\Users\\admin\\IdeaProjects\\studentManager\\src\\studentInfo.txt", true), true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            pw.println(s);
            pw.close();

            showJDialog("添加对象成功！");
            System.out.println("添加对象成功");

        } else if (e.getSource() == jButton2) {

            //让增加学生的组件：姓名 年龄 住址消失，只需要输入学号就能删除学生信息
            sidText.setVisible(false);
            sidText_.setVisible(false);
            nameText.setVisible(false);
            nameText_.setVisible(false);
            ageText.setVisible(false);
            ageText_.setVisible(false);
            addressText.setVisible(false);
            addressText_.setVisible(false);
            jButton6.setVisible(false);
            //把修改学生的组件关掉
            sidText1.setVisible(false);
            nameText1.setVisible(false);
            ageText1.setVisible(false);
            addressText1.setVisible(false);
            sidText1_.setVisible(false);
            nameText1_.setVisible(false);
            ageText1_.setVisible(false);
            addressText1_.setVisible(false);
            jButton8.setVisible(false);
            //关闭查询学生组件
            panel.setVisible(false);

            System.out.println("用户点击了确认删除学生按钮");

            //这是删除学生的确认按钮
            jButton7.setBounds(240, 360, 90, 40);
            add(jButton7);

            getSidText_.setBounds(100, 200, 160, 50);
            getSidText.setBounds(240, 200, 200, 35);
            add(getSidText);
            add(getSidText_);

            //重新开启被其他按钮关闭的组件显示
            getSidText.setVisible(true);
            getSidText_.setVisible(true);
            jButton7.setVisible(true);

        } else if (e.getSource() == jButton7) {
            System.out.println("点击了删除学生的确认按钮");

            boolean flag = false;
            String sid = getSidText.getText();

            for (int i = 0; i < array.size(); i++) {
                Student s = array.get(i);
                if (s.getSid().equals(sid)) {
                    array.remove(i);
                    //同时要移除文件中的对应学号的那条记录
                    //这时候集合中的元素已经完成了更新
                    //创建字符打印流的对象
                    //这时候第一次不需要续写，重新把集合中的数据添加到文件当中即可
                    try {
                        changedArraytoFile(array);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    flag = true;
                    break;
                }
            }

            if (flag) {
                showJDialog("删除学生信息成功");
            } else {
                showJDialog("无该学生学号，请核对后重新输入学号");
            }

        } else if (e.getSource() == jButton3) {
            System.out.println("用户点击了修改学生按钮");

            //开启修改学生的组件
            jButton8.setVisible(true);
            sidText1_.setVisible(true);
            nameText1_.setVisible(true);
            ageText1_.setVisible(true);
            addressText1_.setVisible(true);
            sidText1.setVisible(true);
            nameText1.setVisible(true);
            ageText1.setVisible(true);
            addressText1.setVisible(true);

            //关闭添加学生的组件
            sidText.setVisible(false);
            sidText_.setVisible(false);
            nameText.setVisible(false);
            nameText_.setVisible(false);
            ageText.setVisible(false);
            ageText_.setVisible(false);
            addressText.setVisible(false);
            addressText_.setVisible(false);
            jButton6.setVisible(false);
            //关闭删除学生的组件
            jButton7.setVisible(false);
            getSidText.setVisible(false);
            getSidText_.setVisible(false);
            //关闭查询学生组件
            panel.setVisible(false);

            //当点击了添加学生按钮后会出现确认按钮
            jButton8.setBounds(240, 360, 90, 40);
            add(jButton8);

            //展示要输入信息的提示和输入框
            sidText1_.setBounds(50, 150, 150, 50);
            nameText1_.setBounds(50, 200, 150, 50);
            ageText1_.setBounds(50, 250, 150, 50);
            addressText1_.setBounds(50, 300, 150, 50);
            add(sidText1_);
            add(nameText1_);
            add(ageText1_);
            add(addressText1_);
            //输入框
            sidText1.setBounds(210, 150, 200, 35);
            nameText1.setBounds(210, 200, 200, 35);
            ageText1.setBounds(210, 250, 200, 35);
            addressText1.setBounds(210, 300, 200, 35);
            add(sidText1);
            add(nameText1);
            add(ageText1);
            add(addressText1);


        } else if (e.getSource() == jButton8) {
            //当点击了确认修改的按钮

            //保证要修改的学生的学号是之前已经有的，即在原来的学号上改变其他学生信息
            boolean flag = false;

            String sid1 = sidText1.getText();

            for (int i = 0; i < array.size(); i++) {
                Student s = array.get(i);
                if (s.getSid().equals(sid1)) {
                    flag = true;
                    break;
                }
            }

            if (flag == false) {
                System.out.println("输入修改学生的学号有误，请重新检查后输入");
                showJDialog("输入修改学生的学号有误，请重新检查后输入");
                return;
            }

            //System.out.println("请输入要修改学生的姓名：");
            String name1 = nameText1.getText();
            //System.out.println("请输入要修改学生的年龄：");
            String age1 = ageText1.getText();
            //System.out.println("请输入要修改学生的地址：");
            String address1 = addressText1.getText();

            Student s = new Student(sid1, name1, age1, address1);

            for (int i = 0; i < array.size(); i++) {
                Student stu = array.get(i);
                //遍历找出要修改的学生的位置
                if (stu.getSid().equals(sid1)) {
                    array.set(i, s);//用s修改i位置的元素
                    break;
                }
            }

            //此时array已经被修改，要重新将array中的信息传至文件中
            try {
                changedArraytoFile(array);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            System.out.println("修改学生信息成功");
            showJDialog("修改学生信息成功！");


        } else if (e.getSource() == jButton4) {

            System.out.println("用户点击了查询学生按钮");
            //关闭添加学生的组件
            sidText.setVisible(false);
            sidText_.setVisible(false);
            nameText.setVisible(false);
            nameText_.setVisible(false);
            ageText.setVisible(false);
            ageText_.setVisible(false);
            addressText.setVisible(false);
            addressText_.setVisible(false);
            jButton6.setVisible(false);
            //关闭删除学生的组件
            jButton7.setVisible(false);
            getSidText.setVisible(false);
            getSidText_.setVisible(false);
            //开启修改学生的组件
            jButton8.setVisible(false);
            sidText1_.setVisible(false);
            nameText1_.setVisible(false);
            ageText1_.setVisible(false);
            addressText1_.setVisible(false);
            sidText1.setVisible(false);
            nameText1.setVisible(false);
            ageText1.setVisible(false);
            addressText1.setVisible(false);

            panel.setVisible(true);
            //使带有学生信息的表格呈现出来
            // 创建滚动面板并将表格视图添加到其中

            // 删除所有已有的列和行，这样多次点击查询学生按钮时才能正常
            tableModel.setColumnCount(0);
            tableModel.setRowCount(0);

            JScrollPane scrollPane = new JScrollPane(table);

            //只添加一次表头
            tableModel.addColumn("学号");
            tableModel.addColumn("姓名");
            tableModel.addColumn("年龄");
            tableModel.addColumn("住址");


            for (Student student : array) {
                Object[] rowData = {student.getSid(), student.getName(), student.getAge(), student.getAddress()};
                tableModel.addRow(rowData);
            }
            panel.setBounds(40, 140, 450, 280);
            // 将滚动面板添加到窗口中
            panel.add(scrollPane);
            panel.setVisible(true);
            add(panel);


        } else if (e.getSource() == jButton5) {
            System.out.println("用户点击了退出系统按钮");
            System.exit(0);
        }
    }

    private boolean isUsed(ArrayList<Student> array, String sid) {
        for (Student student : array) {
            if (student.getSid().equals(sid)) {
                return true;
            }
        }

        return false;
    }

    private static void changedArraytoFile(ArrayList<Student> arrays) throws IOException {
        int temp1 = 0;
        //如果此时集合里没有元素，直接覆盖原有的文件
        if (arrays.size() == 0) {
            PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\admin\\IdeaProjects\\studentManager\\src\\studentInfo.txt", false), true);
            pw.close();
        } else {
            for (Student student : arrays) {
                if (temp1 == 0) {
                    PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\admin\\IdeaProjects\\studentManager\\src\\studentInfo.txt", false), true);
                    pw.println(student);
                    pw.close();
                    temp1 = 1;//第一次续写完成后后面就应该开启续写
                } else {
                    PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\admin\\IdeaProjects\\studentManager\\src\\studentInfo.txt", true), true);
                    pw.println(student);
                    pw.close();
                }
            }
        }

    }

    private static void readStuInfo(ArrayList<Student> array) throws IOException {

        //利用BufferedReader流来读取文件中的数据
        //InputStreamReader转换流
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("C:\\Users\\admin\\IdeaProjects\\studentManager\\src\\studentInfo.txt"), "UTF-8"));

        String line;
        System.out.println("学生管理系统在操作前中已经保存的学生数据为：");
        while ((line = br.readLine()) != null) {
            //打印每一行数据 (即每一个学生的信息）
            System.out.println(line);

            String[] stuInfoArr = line.split(",");

            String[] arr1 = stuInfoArr[0].split("=");
            String[] arr2 = stuInfoArr[1].split("=");
            String[] arr3 = stuInfoArr[2].split("=");
            String[] arr4 = stuInfoArr[3].split("=");

            Student stu = new Student(arr1[1], arr2[1], arr3[1], arr4[1]);
            array.add(stu);
        }

    }

    private void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(250, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建JLabel对象管理  文字  并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}




