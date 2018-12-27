package com.loupgarou.divers;

import java.util.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

	public class fonctions {
		public static String lireChaine() {
			Scanner myScanner = new Scanner(System.in);
			
			try {
				return myScanner.nextLine();
			}
			
			catch (Exception ex) {
				ex.printStackTrace();
				return "";
			}
		}
		
		public static int lireEntier() {
			Scanner myScanner = new Scanner(System.in);
			
			try {
				return myScanner.nextInt();
			}
			
			catch (Exception ex) {
				return 0;
			}
		}
		
		public static Date stringToSQLDate(String date)
		{
			DateFormat formatter = new SimpleDateFormat("d-M-yyyy");
			Date d = new Date(); 
			try
			{
				d = formatter.parse(date);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			return d;
			
//			java.util.Date StartDate;
//			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//			try 
//			{
//				java.util.Date parsed = format.parse(date);
//				sqlStartDate = new java.sql.Date(parsed.getTime());
//			}
//			catch(ParseException e)
//			{
//				System.out.println("Format non conforme");
//			}
//			return sqlStartDate;
		}
		
		public static Date stringToDate(String date)
		{
			Date d = new Date();
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d H:m:s");
				d = formatter.parse(date);
			}
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return d;
		}
		
		public static String dateToString(Date date)
		{
			String d = new String();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d H:m:s");
			d = formatter.format(date);
			return d;
		}
		
		
//		try {
//			//STRING VERS DATE
//			String laDate = result.getString("UTI_DATE");
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d H:m:s");
//			
//			Date d = formatter.parse(laDate);
//			System.out.println(d);
//			
//			//DATE VERS STRING
//			System.out.println(formatter.format(d));
//		}
//		
//		catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
