/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import pojo.CheckTable;
import pojo.PunchCard;
import pojo.RepairCard;
import pojo.Salary;

/**
 *
 * @author chenshihang
 */
public class TableDaoImpl implements TableDao {

    private Connection conn;
    private PreparedStatement pstmt;

    public TableDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<PunchCard> showDaDao() throws Exception {
        String sql = "select  * from PunchCard  ";
        List<PunchCard> pl = new ArrayList<>();
        PunchCard p = null;
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            p = new PunchCard();
            p.setPid(rs.getInt(1));
            p.setPno(rs.getString(2));
            p.setPName(rs.getString(3));
            p.setPDate(rs.getTimestamp(4));
            p.setPdesc(rs.getString(5));
            pl.add(p);
        }

        System.out.println("dao.TableDaoImpl.showDaDao()" + pl);
        return pl;
    }
    //更新下午打卡时间，数据来源于打卡单
    private void updateATime(PunchCard p) throws Exception {
        String sql = "UPDATE checktable set KATime =? WHERE KName=?  AND  (  DAY(?) = Day(KATime) or DAY(?)= DAY(KMTime)  ) ";
        pstmt = conn.prepareStatement(sql);
        pstmt.setTimestamp(1, p.getPDate());
        pstmt.setString(2, p.getPName());
        pstmt.setTimestamp(3, p.getPDate());
        pstmt.setTimestamp(4, p.getPDate());
        boolean flag = true;
        flag = pstmt.execute();
    }
   //更新下午打卡时间，数据来源于补卡单
    private void updateATime(RepairCard r) throws Exception {
        String sql = "UPDATE checktable set KATime =? WHERE KName=?  AND  (  DAY(?) = Day(KATime) or DAY(?)= DAY(KMTime)  ) ";
        pstmt = conn.prepareStatement(sql);
        pstmt.setTimestamp(1, r.getRDate());
        pstmt.setString(2, r.getRName());
        pstmt.setTimestamp(3, r.getRDate());
        pstmt.setTimestamp(4, r.getRDate());
        boolean flag = true;
        flag = pstmt.execute();
    }
   //更新早上打卡时间，数据来源于打卡单
    private void updateMTime(PunchCard p) throws Exception {
        String sql = "UPDATE checktable set KMTime =? WHERE KName=?  AND  (  DAY(?) = Day(KATime) or DAY(?)= DAY(KMTime)  ) ";
        pstmt = conn.prepareStatement(sql);
        pstmt.setTimestamp(1, p.getPDate());
        pstmt.setString(2, p.getPName());
        pstmt.setTimestamp(3, p.getPDate());
        pstmt.setTimestamp(4, p.getPDate());
        boolean flag = true;
        flag = pstmt.execute();
    }
   //更新早上打卡时间，数据来源于补卡单
    private void updateMTime(RepairCard r) throws Exception {
        String sql = "UPDATE checktable set KMTime =? WHERE KName=?  AND  (  DAY(?) = Day(KATime) or DAY(?)= DAY(KMTime)  ) ";
        pstmt = conn.prepareStatement(sql);
        pstmt.setTimestamp(1, r.getRDate());
        pstmt.setString(2, r.getRName());
        pstmt.setTimestamp(3, r.getRDate());
        pstmt.setTimestamp(4, r.getRDate());
        boolean flag = true;
        flag = pstmt.execute();
    }
    //将打卡信息添加到checktable 
    private void insertInfoByKname(PunchCard p) throws Exception {
        int t = p.getPDate().getHours();
        //分辨早上下午打卡  默认 以中午12：00 为界
        if (t >= 12) {
            int num = 0;
            //判断是否有该员工的当天打卡信息 有调用updateAtime 没有插入信息
            String sql2 = "SELECT COUNT(KMTime or KATime) from checktable   where  KName = ?  AND ( DAY(KMTime) =DAY(?) OR DAY(KATime) =DAY(?) )";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, p.getPName());
            pstmt.setTimestamp(2, p.getPDate());
            pstmt.setTimestamp(3, p.getPDate());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                num = rs.getInt(1);
                System.out.println("数目：" + num);
            }

            if (num != 0) {
                updateATime(p);
            } else {
                String sql3 = "insert into checktable set KCode=?,KName=?,KATime=?";
                pstmt = conn.prepareStatement(sql3);
                pstmt.setString(1, p.getPno());
                pstmt.setString(2, p.getPName());
                pstmt.setTimestamp(3, p.getPDate());
                pstmt.execute();
                updateATime(p);
            }
        } else {
            //早上打卡插入 
            int num = 0;
            String sql2 = "SELECT COUNT(KMTime or KATime) from checktable   where  KName = ?  AND ( DAY(KMTime) =DAY(?) OR DAY(KATime) =DAY(?) )";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, p.getPName());
            pstmt.setTimestamp(2, p.getPDate());
            pstmt.setTimestamp(3, p.getPDate());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                num = rs.getInt(1);
                System.out.println("数目：" + num);
            }

            if (num != 0) {
                updateMTime(p);
            } else {
                String sql3 = "insert into checktable set KCode=?,KName=?,KMTime=?";
                pstmt = conn.prepareStatement(sql3);
                pstmt.setString(1, p.getPno());
                pstmt.setString(2, p.getPName());
                pstmt.setTimestamp(3, p.getPDate());
                pstmt.execute();
                updateMTime(p);
            }
        }
    }
    //将补卡信息添加到checktable 方法同上 
    private void insertInfoByRname(RepairCard r) throws Exception {
        int t = r.getRDate().getHours();
        //分辨早上下午打卡
        if (t >= 12) {
            int num = 0;
            String sql2 = "SELECT COUNT(KMTime or KATime) from checktable   where  KName = ?  AND ( DAY(KMTime) =DAY(?) OR DAY(KATime) =DAY(?) )";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, r.getRName());
            pstmt.setTimestamp(2, r.getRDate());
            pstmt.setTimestamp(3, r.getRDate());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                num = rs.getInt(1);
                System.out.println("数目：" + num);
            }

            if (num != 0) {
                updateATime(r);
            } else {
                String sql3 = "insert into checktable set KCode=?,KName=?,KATime=?";
                pstmt = conn.prepareStatement(sql3);
                pstmt.setString(1, r.getRno());
                pstmt.setString(2, r.getRName());
                pstmt.setTimestamp(3, r.getRDate());
                pstmt.execute();
                updateATime(r);
            }
        } else {
            int num = 0;
            String sql2 = "SELECT COUNT(KMTime or KATime) from checktable   where  KName = ?  AND ( DAY(KMTime) =DAY(?) OR DAY(KATime) =DAY(?) )";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, r.getRName());
            pstmt.setTimestamp(2, r.getRDate());
            pstmt.setTimestamp(3, r.getRDate());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                num = rs.getInt(1);
                System.out.println("数目：" + num);
            }

            if (num != 0) {
                updateMTime(r);
            } else {
                String sql3 = "insert into checktable set KCode=?,KName=?,KMTime=?";
                pstmt = conn.prepareStatement(sql3);
                pstmt.setString(1, r.getRno());
                pstmt.setString(2, r.getRName());
                pstmt.setTimestamp(3, r.getRDate());
                pstmt.execute();
                updateMTime(r);
            }
        }
    }

    @Override
    public boolean AddDaTableDao(PunchCard p) throws Exception {
        boolean flag = true;
        String sql = "insert into PunchCard(Pno,PName,PDate,Pdesc) values(?,?,?,?) ";
        pstmt = conn.prepareStatement(sql);
        System.out.println("这里p=？" + p.getPno());
        pstmt.setString(1, p.getPno());
        pstmt.setString(2, p.getPName());
        pstmt.setTimestamp(3, p.getPDate());
        pstmt.setString(4, p.getPdesc());
        pstmt.execute();
        //调用添加至checktable
        insertInfoByKname(p);
        return flag;
    }

    public List<RepairCard> showReDao() throws Exception {
        String sql = "select  * from RepairCard  ";
        List<RepairCard> rl = new ArrayList<>();
        RepairCard r = null;
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            r = new RepairCard();
            r.setRId(rs.getInt(1));
            r.setRno(rs.getString(2));
            r.setRName(rs.getString(3));
            r.setRDate(rs.getTimestamp(4));
            r.setRdesc(rs.getString(5));
            rl.add(r);
        }
        System.out.println("dao.TableDaoImpl.showReDao()" + rl);
        return rl;
    }

    @Override
    public boolean AddReTableDao(RepairCard r) throws Exception {
        boolean flag = true;
        String sql = "insert into RepairCard(Rno,RName,RDate,Rdesc) values(?,?,?,?) ";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, r.getRno());
        pstmt.setString(2, r.getRName());
        pstmt.setTimestamp(3, r.getRDate());
        pstmt.setString(4, r.getRdesc());
        pstmt.execute();
        //调用添加至checktable
        insertInfoByRname(r);
        return flag;

    }
  //显示考勤表和根据上午下午的考勤信息添加出勤状况        
    @Override
    public List<CheckTable> showChAndAddState(int month) throws Exception {
        List<CheckTable> cl = new ArrayList<>();
        List<String> limitTime = new ArrayList<>();
        CheckTable c = null;

        String sql = "select KMTime,KATime,KCode,Kid from CheckTable where MONTH(KATime) =? or MONTH(KMTime)=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, String.valueOf(month));
        pstmt.setString(2, String.valueOf(month));
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {

            //SELECT MTime,ATime from  classes  WHERE  ClassCode = (SELECT EClassCode from employees WHERE Eno='aa')
            limitTime = getLimitTime(rs.getString(3));
            try {
                String MTime = limitTime.get(0);
                String ATime = limitTime.get(1);
                System.out.println("LIMIT time = ?" + limitTime);
                try {
                    String state = GetState(rs.getTimestamp(1), rs.getTimestamp(2), MTime, ATime);
                    String sql1 = "update  CheckTable set state =? where Kid=?";
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setString(1, state);
                    pstmt.setInt(2, rs.getInt(4));
                    pstmt.execute();

                } catch (Exception e) {
                    String sql1 = "update  CheckTable set state =? where Kid=?";
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setString(1, "旷工");
                    pstmt.setInt(2, rs.getInt(4));
                    pstmt.execute();
                }
            } catch (Exception e) {
                System.out.println("cl长度" + cl.size());
                return cl;
            }

        }
        // 显示功能实现
        String sql2 = "select * from checktable";
        pstmt = conn.prepareStatement(sql2);
        ResultSet rs2 = pstmt.executeQuery();
        while (rs2.next()) {
            c = new CheckTable();
            c.setKid(rs2.getInt(1));
            c.setKCode(rs2.getString(2));
            c.setKName(rs2.getString(3));
            try {
                c.setKMTime(rs2.getTimestamp(4));
                c.setKATime(rs2.getTimestamp(5));
            } catch (Exception e) {
                /*
                 datetime数据库中可为空，其默认值为“0000-00-0000:00:00”，
                因MySQL的时间类型datetime范围是1000-01-01 00:00:00 到 9999-12-31 23:59:59，所以报错。
                这里捕获异常但是不处理
                */
                System.out.println(" Value '0000-00-00 00:00:00' can not be represented as java.sql.Timestamp");

            }
            c.setState(rs2.getString(6));
            cl.add(c);
        }
        pstmt.close();
        return cl;
    }
    //获取出勤状态
    private String GetState(Timestamp timestamp1, Timestamp timestamp2, String MTime, String ATime) {
        String state = "正常出勤";
        int Mhour = timestamp1.getHours();  //  早上用户登入时间
        int Mminute = timestamp1.getMinutes();
        int subHourMTime = Integer.parseInt(MTime.substring(0, 2)); // 早上迟到时间
        int subMinuteMTime = Integer.parseInt(MTime.substring(3, 5));

        if (Mhour > subHourMTime || (Mhour == subHourMTime && Mminute > subMinuteMTime)) {
            state = "早上迟到";
            return state;
        }

        int Ahour = timestamp2.getHours();
        int Aminute = timestamp2.getMinutes();
        int subHourATime = Integer.parseInt(ATime.substring(0, 2));
        int subMinuteATime = Integer.parseInt(ATime.substring(3, 5));

        if (Ahour < subHourATime || (Ahour == subHourATime && Aminute <= subMinuteATime)) {
            state = "下午早退";
            return state;
        }

        System.out.println("测试getState ：" + timestamp1 + timestamp2 + MTime + ATime);
//        System.out.println("测试截取时间"+subHour+"min"+subMinute);

        return state;
    }
   //从班次表 获取员工的班次信息进行出勤情况的判断
    private List<String> getLimitTime(String string) throws Exception {
        List<String> li = new ArrayList<>();
        String sql = "select MTime,ATime from classes where ClassCode=(select EClassCode from employees where Eno=?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, string);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            li.add(rs.getString(1));
            li.add(rs.getString(2));
        }
        return li;
    }
    //  队考勤表搜索功能的实现
    public List<CheckTable> searchCheckAllDao(String parameter, String parameter0, String parameter1) throws Exception {
        List<CheckTable> cl = new ArrayList<>();
        CheckTable c = null;
        String Year = parameter.substring(0, 4);
        String startMonth = parameter.substring(5, 7);
        String startDay = parameter.substring(8, 10);
        String endMonth = parameter0.substring(5, 7);
        String endDay = parameter0.substring(8, 10);
        System.out.println("测试日期" + Year + startMonth + startDay + " end" + endMonth + endDay);

        //SELECT * from checktable  WHERE KName = 'Admin' AND YEAR(KMTime)='2019' AND Month(KMTime)='6' AND DAY(KMTime) BETWEEN 20 AND 27 
        String sql
                = "select *from checktable where( KName=? or KCode=?) and ( (YEAR(KMTime)>=? and MONTH(KMTime) BETWEEN ? AND ?  AND DAY(KMTime) BETWEEN ? AND ?)  or ( YEAR(KATime)>=? and MONTH(KATime) BETWEEN ? AND ?  AND DAY(KATime) BETWEEN ? AND ? ) )";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, parameter1);
        pstmt.setString(2, parameter1);
        pstmt.setString(3, Year);
        pstmt.setString(4, startMonth);
        pstmt.setString(5, endMonth);
        pstmt.setString(6, startDay);
        pstmt.setString(7, endDay);
        pstmt.setString(8, Year);
        pstmt.setString(9, startMonth);
        pstmt.setString(10, endMonth);
        pstmt.setString(11, startDay);
        pstmt.setString(12, endDay);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            c = new CheckTable();
            c.setKid(rs.getInt(1));
            c.setKCode(rs.getString(2));
            c.setKName(rs.getString(3));
            try {
                c.setKMTime(rs.getTimestamp(4));
                c.setKATime(rs.getTimestamp(5));
            } catch (Exception e) {
                        /*
                 datetime数据库中可为空，其默认值为“0000-00-0000:00:00”，
                因MySQL的时间类型datetime范围是1000-01-01 00:00:00 到 9999-12-31 23:59:59，所以报错。
                这里捕获异常但是不处理
                */
            }
            c.setState(rs.getString(6));
            cl.add(c);

        }
        System.out.println("输出cl" + cl);
        return cl;
    }

    public List<Salary> showSalaryDao() throws Exception {
        List<Salary> sl = new ArrayList<>();
        Salary s = null;
        String sql = "select *from salary";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            s = new Salary();
            s.setSId(rs.getInt(1));
            s.setSCode(rs.getString(2));
            s.setSName(rs.getString(3));
            s.setSalary(rs.getBigDecimal(4));
            s.setSDate(rs.getDate(5));
            s.setEDate(rs.getDate(6));
            sl.add(s);
        }

        return sl;
    }

    @Override
    public boolean addSalaryDao(Salary s) throws Exception {
        boolean flag = true;
        BigDecimal sa = s.getSalary();
        double salary = sa.doubleValue();
        List<String> limitTime = new ArrayList<>();
        limitTime = getLimitTime(s.getSCode());
        try {
            String MTime = limitTime.get(0);
            String ATime = limitTime.get(1);
            int MHour = Integer.parseInt(MTime.substring(0, 2));
            int AHour = Integer.parseInt(ATime.substring(0, 2));
            System.out.println("早上下午" + MHour + AHour);

            System.out.println("LIMIT time = ?" + limitTime);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = df.format(s.getSDate());
            String eDate = df.format(s.getEDate());
            System.out.println("dao开始和结束日期" + sDate + eDate);

            String Year = sDate.substring(0, 4);
            String startMonth = sDate.substring(5, 7);
            String startDay = sDate.substring(8, 10);

            String endMonth = eDate.substring(5, 7);
            String endDay = eDate.substring(8, 10);
            //SELECT * from checktable  WHERE KName = 'Admin' AND YEAR(KMTime)='2019' AND Month(KMTime)='6' AND DAY(KMTime) BETWEEN 20 AND 27 
            String sql
                    = "select  KMTime,KATime,state from checktable where( KName=? or KCode=?) and ( (YEAR(KMTime)>=? and MONTH(KMTime) BETWEEN ? AND ?  AND DAY(KMTime) BETWEEN ? AND ?)  or ( YEAR(KATime)>=? and MONTH(KATime) BETWEEN ? AND ?  AND DAY(KATime) BETWEEN ? AND ? ) )";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s.getSName());
            pstmt.setString(2, s.getSCode());
            pstmt.setString(3, Year);
            pstmt.setString(4, startMonth);
            pstmt.setString(5, endMonth);
            pstmt.setString(6, startDay);
            pstmt.setString(7, endDay);
            pstmt.setString(8, Year);
            pstmt.setString(9, startMonth);
            pstmt.setString(10, endMonth);
            pstmt.setString(11, startDay);
            pstmt.setString(12, endDay);
            ResultSet rs = pstmt.executeQuery();
            int sumHour = 0;
            while (rs.next()) {
                // 统计工作时间
                try {
                    Timestamp KMTime = rs.getTimestamp(1);
                    sumHour = sumHour + (12 - MHour);
                    System.out.println(KMTime.getHours());
                } catch (Exception e) {
                    Timestamp KMTime = null;
                }

                try {
                    Timestamp KATime = rs.getTimestamp(2);
                    sumHour = sumHour + (AHour - 12);
                } catch (Exception e) {
                    Timestamp KATime = null;
                    System.out.println("??");
                }
                System.out.println("查找条数1");
            }
            double sumSalary = sumHour * (salary);
            s.setSalary(new BigDecimal(sumSalary));
            System.out.println("Sum时间：" + sumHour);
            String sql2 = "insert into salary(SCode,SName,Salary,SDate,EDate) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, s.getSCode());
            pstmt.setString(2, s.getSName());
            pstmt.setBigDecimal(3, s.getSalary());
            pstmt.setDate(4, new java.sql.Date(s.getSDate().getTime()));
            pstmt.setDate(5, new java.sql.Date(s.getEDate().getTime()));
            pstmt.execute();
        } catch (Exception e) {
            return false;
        }
        return flag;
    }

    @Override
    public boolean checkEmpByNameAndCode(Salary s) throws Exception {
        boolean flag = false;
        String sql = "select Emp_Id from employees where Eno=? and Ename=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, s.getSCode());
        pstmt.setString(2, s.getSName());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            flag = true;
            break;
        }
        return flag;
    }

    @Override
    public boolean deleteSalaryDao(String Sid) throws Exception {
        boolean flag = true;
        String sql = "delete from salary where SId =? ";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.parseInt(Sid));
        pstmt.execute();
        return flag;
    }
}
