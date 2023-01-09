package com.company;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

class StudentClass{
    public int size = 4;
    public boolean isemptyFile = false;

    public boolean isemptyFile() {
        return isemptyFile;
    }

    //添加一个学生
    public void addOneStudent(Student addstu,Student[] students){
        students[size+1] = addstu;
        size++;
    }
    //删除一个学生
    public void delOneStudent(String name,Student[] students){
        for(int i = 0;i <= size;i++){
            if(students[i].name.equals(name))  {
                students[i] = students[size];
                students[size] = null;
                break;
            }
        }
        size--;
    }
    //根据名字查询学生是否存在
    public boolean findStudent(String name,Student[] students){
        boolean flag = false;
        for(int i = 0;i <= size;i++){
            if(students[i].name.equals(name))  {
                flag = true;
                break;
            }
        }
        if(flag) return true;
        else return false;
    }
    //按总成绩从大到小排序
    public void sortByTotalScore(Student[] students)
    {
        for(int i = 0;i < size;i++)
        {
            int maxm = students[i].totalScore,maxi = i;
            for(int j = i + 1;j <= size;j++)
            {
                if(students[j].totalScore > maxm) {
                    maxm = students[j].totalScore;
                    maxi = j;
                }
            }
            Student temp=students[maxi];
            students[maxi]=students[i];
            students[i]=temp;
        }
    }

    public void writeDataToFile(Student[] students){

        try {
            File file = new File("res/students.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(fileWriter);
            for(int i = 0;i <= size;i++)
            {
                out.write(students[i].Sno+'\n');
                out.write(students[i].name+'\n');
                out.write(String.valueOf(students[i].englishScore)+'\n');
                out.write(String.valueOf(students[i].mathScore)+'\n');
                out.write(String.valueOf(students[i].computerScore)+'\n');
                out.write(String.valueOf(students[i].totalScore)+'\n');
            }
            out.close();
        }
        catch (Exception e){
            System.out.println("failed!"+e);
        }
    }

    public void ReadDataFromFile(Student[] students)
    {
        try {
            File file = new File("res/students.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String FileInputLine = null;

            String temp="";

            if((FileInputLine = br.readLine()) == null)
                isemptyFile = true;
            else
                temp = FileInputLine;

            //文件非空 学生信息从文件读取
            if(isemptyFile == false)
                for(int i = 0;i <= 9;i++)
                {
                    Student student = new Student();
                    if(i != 0) {
                        FileInputLine = br.readLine();
                        if (FileInputLine == null) break;
                        student.Sno = FileInputLine;
                    }
                    else
                        student.Sno = temp;
                    FileInputLine = br.readLine();
                    if(FileInputLine == null) break;
                    student.name = FileInputLine;

                    FileInputLine = br.readLine();
                    if(FileInputLine == null) break;
                    student.englishScore = Integer.valueOf(FileInputLine);

                    FileInputLine = br.readLine();
                    if(FileInputLine == null) break;
                    student.mathScore = Integer.valueOf(FileInputLine);;

                    FileInputLine = br.readLine();
                    if(FileInputLine == null) break;
                    student.computerScore = Integer.valueOf(FileInputLine);

                    FileInputLine = br.readLine();
                    if(FileInputLine == null) break;
                    student.totalScore = Integer.valueOf(FileInputLine);

                    students[i] = student;

                }
            for(int i = 0;i <= 4;i++)
            {
                System.out.println("Sno:"+students[i].Sno);
            }
        }
        catch (Exception e){

        }
    }
}

class Student{
    public String Sno;
    public String name;
    public int englishScore,mathScore,computerScore,totalScore;

    public Student(){}
    public Student(String sno, String name, int englishScore, int mathScore, int computerScore, int totalScore) {
        Sno = sno;
        this.name = name;
        this.englishScore = englishScore;
        this.mathScore = mathScore;
        this.computerScore = computerScore;
        this.totalScore = totalScore;
    }
}

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("ok");

        Student[] students = new Student[10];
        int size = 0;

        StudentClass studentClass = new StudentClass();


        studentClass.ReadDataFromFile(students);
        // 文件没有存数据 则 键盘输入数据
        if(studentClass.isemptyFile() == true){
            Scanner scanner = new Scanner(System.in);
            for(int i = 0;i <= 4;i++){
                String s = scanner.nextLine();

                //分割字符串
                String[] stu = s.split(" ");

                Student student = new Student();

                student.Sno = stu[0];
                student.name = stu[1];
                student.englishScore = Integer.valueOf(stu[2]);
                student.mathScore = Integer.valueOf(stu[3]);
                student.computerScore = Integer.valueOf(stu[4]);
                student.totalScore = Integer.valueOf(stu[5]);

                students[i] = student;
            }
        }


        //StudentClass操作模拟
        /*
        测试样例
        123 andy 90 80 70 240
        124 qqq 70 70 70 210
        125 www 90 90 90 270
        126 eee 60 60 60 180
        127 rrr 90 80 80 250
         */
        studentClass.sortByTotalScore(students);

        for(int i = 0;i <= studentClass.size;i++)
            System.out.println(students[i].name);

        boolean temp;
        temp = studentClass.findStudent("andy",students);
        System.out.println("学生andy存在吗："+temp);
        temp = studentClass.findStudent("hh",students);
        System.out.println("学生hh存在吗："+temp);

        Student stu = new Student();
        stu.name = "hh";
        stu.Sno = "111";
        stu.englishScore = 70;
        stu.mathScore = 80;
        stu.computerScore = 60;
        stu.totalScore = 210;
        studentClass.addOneStudent(stu,students);
        temp = studentClass.findStudent("hh",students);
        System.out.println("添加hh学生后学生hh存在吗："+ temp);

        studentClass.delOneStudent("andy",students);
        temp = studentClass.findStudent("andy",students);
        System.out.println("删除andy学生后学生andy存在吗："+temp);

        //写入文件
        studentClass.writeDataToFile(students);





    }
}
