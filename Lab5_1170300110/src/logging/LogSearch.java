package logging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogSearch {
	/**
     * ���ܣ�Java��ȡtxt�ļ������� ���裺1���Ȼ���ļ���� 2������ļ��������������һ���ֽ���������Ҫ��������������ж�ȡ
     * 3����ȡ������������Ҫ��ȡ�����ֽ��� 4��һ��һ�е������readline()�� ��ע����Ҫ���ǵ����쳣���
     * 
     * @param filePath
     *            �ļ�·��[�����ļ�:�磺 D:\aa.txt]
     * @return ������ļ�����ÿһ���и�������ŵ�list�С�
     */
    private static List<String> readInfo()
    {
    	String filePath = "logs/log.log";
        List<String> list = new ArrayList<String>();
        try
        {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists())
            { // �ж��ļ��Ƿ����
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// ���ǵ������ʽ
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("�Ҳ���ָ�����ļ�");
            }
        }
        catch (Exception e)
        {
            System.out.println("��ȡ�ļ����ݳ���");
            e.printStackTrace();
        }

        return list;
    }
    
//    private static List<String> readError()
//    {
//    	String filePath = "logs/error.log";
//        List<String> list = new ArrayList<String>();
//        try
//        {
//            String encoding = "GBK";
//            File file = new File(filePath);
//            if (file.isFile() && file.exists())
//            { // �ж��ļ��Ƿ����
//                InputStreamReader read = new InputStreamReader(
//                        new FileInputStream(file), encoding);// ���ǵ������ʽ
//                BufferedReader bufferedReader = new BufferedReader(read);
//                String lineTxt = null;
//
//                while ((lineTxt = bufferedReader.readLine()) != null)
//                {
//                    list.add(lineTxt);
//                }
//                bufferedReader.close();
//                read.close();
//            }
//            else
//            {
//                System.out.println("�Ҳ���ָ�����ļ�");
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("��ȡ�ļ����ݳ���");
//            e.printStackTrace();
//        }
//
//        return list;
//    }
    
    private static boolean between(String start, String end, String str) {
    	boolean res = false;
    	
    	int yearStart = Integer.parseInt(start.split("-")[0]);
    	int yearEnd = Integer.parseInt(end.split("-")[0]);
    	int monthStart = Integer.parseInt(start.split("-")[1]);
    	int monthEnd = Integer.parseInt(end.split("-")[1]);
    	int dayStart = Integer.parseInt(start.split("-")[2]);
    	int dayEnd = Integer.parseInt(end.split("-")[2]);
    	
    	int yearNow = Integer.parseInt(str.split("-")[0]);
    	int monthNow = Integer.parseInt(str.split("-")[1]);
    	int dayNow = Integer.parseInt(str.split("-")[2]);
    	
    	if(yearNow < yearEnd && yearNow > yearStart) {
    		res = true;
    	}
    	
    	if(monthNow < monthEnd && monthNow > monthStart) {
    		if(yearNow == yearEnd && yearNow == yearStart) {
        		res = true;
        	}
    	}
    	
    	if(dayNow <= dayEnd && dayNow >= dayStart) {
    		if(monthNow == monthEnd && monthNow == monthStart) {
    			if(yearNow == yearEnd && yearNow == yearStart) {
    				res = true;
    			}
        	}
    	}
    	
    	return res;
    	
    }
    public static void main(String[] args) {
    	List<String> info = readInfo();
    	//List<String> error = readError();
    	
		Scanner in = new Scanner(System.in);
		System.out.println("�������ѯ���ͣ���ʱ�䡢������(error���ͺ�info����)��������");
		boolean flag = true;
		while(flag) {
			String strInput = in.nextLine();
			if(strInput.equals("��ʱ��")) {
				System.out.println("�����뿪ʼʱ�䣺eg: 2019-05-15");
				String start = in.nextLine();
				System.out.println("���������ʱ�䣺eg: 2019-05-17");
				String end = in.nextLine();
				
				for(int i = 0; i < info.size(); i++) {
					if(between(start, end, info.get(i).split(" ")[0])) {
						System.out.println(info.get(i));
					}
				}
				
				flag = false;
			}else if(strInput.equals("������")) {
				System.out.println("���������ͣ�error��info");
				String str = in.nextLine();
				
				if(str.equals("error")) {
					for(int i = 0; i < info.size(); i++) {
						if(info.get(i).contains("ERROR")) {
							System.out.println(info.get(i));
						}
					}
				}else if(str.equals("info")) {
					for(int i = 0; i < info.size(); i++) {
						if(info.get(i).contains("INFO")) {
							System.out.println(info.get(i));
						}
					}
				}else {
					System.out.println("��������˳�����");
				}
				flag = false;
			}else if(strInput.equals("������")) {
				System.out.println("����������ͣ���ӻ�ɾ��");
				String str = in.nextLine();
				
				if(str.equals("���")) {
					for(int i = 0; i < info.size(); i++) {
						if((info.get(i).contains("���") || info.get(i).contains("����")) && 
								!info.get(i).contains("����")) {
							System.out.println(info.get(i));
						}
					}
				}else if(str.equals("ɾ��")) {
					for(int i = 0; i < info.size(); i++) {
						if(info.get(i).contains("ɾ��")  && !info.get(i).contains("����")) {
							System.out.println(info.get(i));
						}
					}
				}else {
					System.out.println("��������˳�����");
				}
				
				flag = false;
			}else {
				flag = true;
			}
		}
		
		in.close();
	}
    
}
