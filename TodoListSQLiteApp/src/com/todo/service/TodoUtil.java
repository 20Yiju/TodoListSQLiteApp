package com.todo.service;

import java.util.*;
import java.io.*;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, date, cate, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("ADD item You want!!\n"
				+ "Write the category of item to ADD > ");
		cate = sc.next();
		sc.nextLine();
		System.out.println("Write the title of item to ADD > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("Title can't be duplicate...");
			return;
		}
		sc.nextLine();
		System.out.println("Write the due date of item to ADD > ");
		due_date = sc.next().trim();
		
		sc.nextLine();
		System.out.println("Write the description of title you wirte > ");
		desc = sc.nextLine().trim();
		
		
		TodoItem t = new TodoItem(title, desc, cate, due_date);
		list.addItem(t);
		System.out.println("ADD successfully:D");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("DELETE item You Want!!\n"
				+ "Write the Number of item to DELETE > ");
		int ind = sc.nextInt();
		System.out.println(ind + ". " + l.getItem(ind-1).toString());
		sc.nextLine();
		System.out.println("Are you sure to erase the above items? (y/n)");
		String yn = sc.next().trim();
		if(yn.equals("y")) {
			Object item = l.getItem(ind-1);
			l.deleteItem((TodoItem) item);
			System.out.println("DELETE successfully:D");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("EDIT item You Want!!\n"
				+ "Write the number of the item you want to EDIT > " );
		int ind = sc.nextInt();
		if (ind > l.length()) {
			System.out.println("Number doesn't exist...");
			return;
		}
		System.out.println(ind + ". " + l.getItem(ind-1).toString());
		sc.nextLine();
		System.out.println("Write the NEW title of the item > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate...");
			return;
		}
		sc.nextLine();
		System.out.println("Write the NEW category of the item > ");
		String new_cate = sc.next().trim();
		sc.nextLine();
		
		System.out.println("Write the new description > ");
		String new_description = sc.nextLine().trim();
		
		sc.nextLine();
		System.out.println("Write the NEW due date of the item > ");
		String new_due = sc.next().trim();
		
		Object item = l.getItem(ind-1);
		l.deleteItem((TodoItem) item);
		TodoItem t = new TodoItem(new_title, new_description, new_cate, new_due);
		l.addItem(t);
		System.out.println("item updated successfuly:D");
		
		/*for (TodoItem item : l.getList()) {
			if (ind == item.getInd()) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_cate, new_due);
				l.addItem(t);
				System.out.println("item updated successfuly:D");
			}
		}*/

	}

	public static void listAll(TodoList l) {
		int c = 1;
		System.out.println("Full list of the list:D , " + l.length() + " items are readed!!");
		
		//System.out.println("[Full list, "+ l.length() + "items are readed]");
		for (TodoItem item : l.getList()) {
			System.out.println(c++ + ". "+ item.toString());
			//System.out.println("[" + item.getTitle() + "] " + ", " + item.getDesc() + "  -  " + item.getCurrent_date());
		}
	}
	public static void saveList(TodoList l, String filename) {
		String path = "/Users/jeong-yiju/git/TodoListApp/" + filename;
		try {
			FileWriter fw = new FileWriter(path);
			for (TodoItem item : l.getList()) {
				fw.write(item.toSaveString());
			}
			System.out.println("All data has been successfully saved!:D");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void loadList(TodoList l, String filename) {
		String path = "/Users/jeong-yiju/git/TodoListApp/" + filename;
		
		try {
			File f = new File(path);
			if(f.exists() == false) {
				System.out.println("The file doesn't exist...");
			}
			else {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line = "";
				int count = 0;
				while((line = br.readLine()) != null) {
					System.out.println(line);
					count++;
				}
				System.out.println(count + "items are readed!!");
				System.out.println("All data has been successfully loaded!:D");
				br.close();
				fr.close();
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void find(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		int c = 0;
		
		System.out.println("Write the word to search > ");
		String search = sc.next().trim();
		sc.nextLine();
		
		for (TodoItem item : l.getList()) {
			if (item.toString().contains(search)) {
				System.out.println(item.toString());
				c++;
			}
		}
		System.out.printf("%d items are founded:D\n", c);
		
	}
	
	
	public static void listCate(TodoList l) {
		
		Set<String> list =  new HashSet<>();
		
		for (TodoItem item : l.getList()) {
			String ca = item.getcate();
			list.add(ca);
			
		}
		
		int c = 0;
		
		for (String item : list) {
			if(c == 0) {
				System.out.print(item);
				c++;
			}
			else {
				System.out.print(" / " + item);
				c++;
			}
			
		}
		System.out.printf("\n %d items are exist!!\n", c);
	}

	public static void findCate(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		int c = 0;
		
		System.out.println("Write the category to search > ");
		String search = sc.next().trim();
		sc.nextLine();
		
		for (TodoItem item : l.getList()) {
			if (item.getcate().contains(search)) {
				System.out.println(item.toString());
				c++;
			}
		}
		System.out.printf("%d items are founded:D\n", c);
		
	}
	

}
