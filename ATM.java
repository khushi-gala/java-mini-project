
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Registration{


   String full_name;
   String date_of_birth;
   int age;
   String gender;
   String address;
   long contact_number;
   String email_id;
   long uidai_number;
   double current_amount;
   String pin;


   public Registration(String full_name, String date_of_birth, int age, String gender, String address,long contact_number, String email_id, long uidai_number, double current_amount, String pin) {
       this.full_name = full_name;
       this.date_of_birth = date_of_birth;
       this.age = age;
       this.gender = gender;
       this.address = address;
       this.contact_number = contact_number;
       this.email_id = email_id;
       this.uidai_number = uidai_number;
       this.current_amount = current_amount;
       this.pin = pin;
   }
}

public class ATM {
   public static void main(String[] args) throws IOException {


       Scanner atm_input = new Scanner(System.in);
       boolean flag = true;
       String check_pin, new_pin, delete_pin;
       double credit, debit;


       String full_name = null;
       String date_of_birth = null;
       int age = 0;
       String gender = null;
       String address = null;
       long contact_number = 0;
       String email_id = null;
       long uidai_number = 0;
       double current_amount = 0;
       String pin = null;


       Map<String, Registration> user_registrations = new HashMap<>();


       System.out.println("\t\t\t\t\tWELCOME TO\n\t\t\t ABCD PRIVATE BANK ATM SERVICES");
       System.out.println("****************************************************************************************");
       System.out.println("\n\nWE ARE HERE TO HELP YOU WITH FOLLOWING THINGS:----------");
       System.out.println("----------------------------------------------------------------------------------------");
       while (flag) {
           System.out.println("\n1.\tREGISTRATION IN ATM\n2.\tCASH DEPOSIT IN ATM\n3.\tCASH WITHDRAWAL FROM ATM\n" +
                   "4.\tCHANGE PIN NUMBER OF ATM\n5.\tDELETE MY RECORD FROM ATM SERVICES\n");
           System.out.println("----------------------------------------------------------------------------------------");
           System.out.print("\nINPUT: ");
           switch (atm_input.nextInt()) {


               case 1:  // registration
                   System.out.println("\n^^^^^^^^^^^^FILL THE FOLLOWING DETAILS^^^^^^^^^^^^^^\n");
                   System.out.print("FULL NAME { SURNAME / NAME / MIDDLE NAME }:\n");
                   atm_input.nextLine(); //to deal with unwanted inputs
                   full_name = atm_input.nextLine();


                   System.out.print("DATE OF BIRTH { DD / MM / YYYY }: ");
                   date_of_birth = atm_input.next();


                   System.out.print("AGE: ");
                   age = atm_input.nextInt();
                   if (age > 85 || age < 10) {
                       System.out.println("sry you are not eligible for registration");
                       break;
                   }
                   System.out.print("GENDER: ");
                   gender = atm_input.next();
                   if (!(gender.equalsIgnoreCase("male") || gender.toString().equalsIgnoreCase("female"))) {
                       System.out.println("wrong input");
                       break;
                   }
                   System.out.print("ADDRESS: ");
                   atm_input.nextLine();
                   address = atm_input.nextLine();
                   System.out.print("CONTACT NUMBER: +91 ");
                   contact_number = atm_input.nextLong();
                   if (Long.toString(contact_number).length() != 10) {
                       System.out.println("wrong input");
                       break;
                   }
                   System.out.print("EMAIL ADDRESS: ");
                   email_id = atm_input.next();
                   if (!email_id.contains("@gmail.com")) {
                       System.out.println("wrong input");
                       break;
                   }
                   System.out.print("UIDAI NUMBER: ");
                   uidai_number = atm_input.nextLong();
                   if (Long.toString(uidai_number).length() != 12) {  // typecasting long into string to calculate length
                       System.out.println("wrong input");
                       break;
                   }
                   System.out.print("CURRENT AMOUNT: ");
                   current_amount = atm_input.nextDouble();
                   if(current_amount % 10 != 0){
                       System.out.println("Amount should be in multiples of 10.");
                       break;
                   }
                   System.out.print("GENERATE PIN { MAX 4 NUMBERS }: ");
                   pin = atm_input.next();
                   if (pin.length() != 4) {
                       System.out.println("wrong input");
                       break;
                   }
                   user_registrations.put(pin, new Registration(full_name, date_of_birth, age, gender, address, contact_number, email_id, uidai_number, current_amount, pin));
                   break;
               case 2: // cash deposit
                   System.out.println("enter your pin: ");
                   check_pin = atm_input.next();
                   if (user_registrations.containsKey(check_pin)) {
                       System.out.println("YOUR DETAILS:\n");
                       System.out.println("NAME: " + user_registrations.get(check_pin).full_name + "\nCONTACT NUMBER: " + user_registrations.get(check_pin).contact_number +
                               "\nCURRENT AMOUNT: " + user_registrations.get(check_pin).current_amount);
                       System.out.print("enter the amount you want to deposit: ");
                       credit = atm_input.nextDouble();
                       if ((credit % 10) == 0) {
                           user_registrations.get(check_pin).current_amount += credit;
                           System.out.println("Amount credited successfully.");
                       } else {
                           System.out.println("amount should be in multiples of 10");
                           break;
                       }
                       break;
                   } else {
                       System.out.println("pin not matched >>>>>>>>>");
                       break;
                   }
               case 3: // cash withdrawal
                   System.out.println("enter your pin: ");
                   check_pin = atm_input.next();
                   if (user_registrations.containsKey(check_pin)) {


                       System.out.println("YOUR DETAILS:\n");
                       System.out.println("NAME: " + user_registrations.get(check_pin).full_name + "\nCONTACT NUMBER: " + user_registrations.get(check_pin).contact_number +
                               "\nCURRENT AMOUNT: " + user_registrations.get(check_pin).current_amount);
                       System.out.print("enter the amount you want to debit: ");
                       debit = atm_input.nextDouble();
                       if ((debit % 10) == 0 && debit <= user_registrations.get(check_pin).current_amount) {
                           user_registrations.get(check_pin).current_amount -= debit;
                           System.out.println("Amount debited successfully.");
                       } else {
                           System.out.println("amount should be in multiples of 10 and less than or equal to the available balance");
                           break;
                       }
                       break;
                   } else {
                       System.out.println("pin not matched >>>>>>>>>");
                       break;
                   }
               case 4: // change pin
                   System.out.println("enter your pin: ");
                   check_pin = atm_input.next();
                   if (user_registrations.containsKey(check_pin)) {
                       System.out.println("YOUR DETAILS:\n");
                       System.out.println("NAME: " + user_registrations.get(check_pin).full_name + "\nCONTACT NUMBER: " + user_registrations.get(check_pin).contact_number +
                               "\nCURRENT AMOUNT: " + user_registrations.get(check_pin).current_amount);
                       System.out.print("enter the new atm pin: ");
                       new_pin = atm_input.next();
                       if (new_pin.length() == 4) {
                           user_registrations.put(new_pin, new Registration(user_registrations.get(check_pin).full_name, user_registrations.get(check_pin).date_of_birth,
                                   user_registrations.get(check_pin).age, user_registrations.get(check_pin).gender, user_registrations.get(check_pin).address,
                                   user_registrations.get(check_pin).contact_number, user_registrations.get(check_pin).email_id, user_registrations.get(check_pin).uidai_number,
                                   user_registrations.get(check_pin).current_amount, new_pin));


                           user_registrations.remove(check_pin);
                           break;
                       } else {
                           System.out.println("pin is incorrect <<<<<<<<<<<<");
                           break;
                       }
                   } else {
                       break;
                   }


               case 5: // delete account
                   System.out.println("enter your pin: ");
                   check_pin = atm_input.next();
                   if (user_registrations.containsKey(check_pin)) {
                       System.out.println("YOUR DETAILS:\n");
                       System.out.println("NAME: " + user_registrations.get(check_pin).full_name + "\nCONTACT NUMBER: " + user_registrations.get(check_pin).contact_number +
                               "\nCURRENT AMOUNT: " + user_registrations.get(check_pin).current_amount);
                       System.out.print("confirm your pin once: ");
                       delete_pin = atm_input.next();
                       if (delete_pin.length() == 4) {
                           user_registrations.remove(delete_pin);
                       } else {
                           System.out.println("pin didn't match the requirements <<<<<<<<<<<<");
                           break;
                       }
                   } else {
                       System.out.println("You have entered the wrong pin...");
                       break;
                   }
               default:
                   System.out.println("thank you for visiting our services........................");
                   flag = false;
                   break;
           }
       }
   }
}
