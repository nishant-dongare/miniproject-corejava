package screens;

import java.util.Scanner;

import functions.ImportCSV;
import functions.Rooms;
import functions.Search;
import models.StudentsDao;

class Admin {

	public Admin() {
		boolean flag = true;

		Scanner sc = new Scanner(System.in);
		while (flag) {
			int choice = 0;
			System.out.print(
					"\n*** Admin Panel ***\n\n1.Import CSV \n2.Save Students Data \n3.Search Student \n4.Assign ClassRoom \n5.Import ClassRoom Data \n6.Export ClassRoom Data \n7.Show All ClassRooms \nEnter your choice : ");
			try {
				choice = sc.nextInt();
			} catch (Exception ime) {
				break;
			}

			switch (choice) {
			case 1:
				new ImportCSV();
				break;

			case 2:
				new StudentsDao().saveStudentsData();
				break;

			case 3:
				new Search();
				break;

			case 4:
				Rooms.assignClassRoom();
				break;

			case 5:
				if (Rooms.importClassRooms())
					System.out.println("\nData Imported Successfully\n");
				break;

			case 6:
				if (Rooms.exportClassRooms())
					System.out.println("\nData Exported Successfully\n");
				break;

			case 7:
				Rooms.showAllClassRooms();
				break;

			default:
				flag = !flag;
			}
		}
	}
}
