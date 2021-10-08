package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.nextLine();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
			
			case "find":
				TodoUtil.find(l);
				break;
			
			case "find_cate":
				TodoUtil.findCate(l);
				break;
				
			case "ls_cate":
				TodoUtil.listCate(l);
				break;

			case "ls_name_asc":
				l.sortByName();
		        System.out.println("This list is arranged in order of titles.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("This list is arranged in reverse order of titles.");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("This list is arranged by date.");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("This list is arranged in reverse order of date.");
				isList = true;
				break;
	
			case "help" :
				Menu.displaymenu();
				break; 

			case "exit":
				quit = true;
				break;

			default:
		        System.out.println("\nEnter the \"help\" if you want to see the menu again!!");
				//System.out.println("please choose one of menu!!");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
