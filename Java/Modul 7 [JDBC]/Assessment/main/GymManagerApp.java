package main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import dao.MemberDAO;
import dao.PaymentDAO;
import dao.TrainerDAO;
import dao.WorkoutPlanDAO;
import model.Member;
import model.Payment;
import model.Trainer;
import model.WorkoutPlan;

// GymManagerApp part - 1

public class GymManagerApp {

    static Scanner sc = new Scanner(System.in);

    static MemberDAO memberDAO = new MemberDAO();
    static TrainerDAO trainerDAO = new TrainerDAO();
    static WorkoutPlanDAO workoutDAO = new WorkoutPlanDAO();
    static PaymentDAO paymentDAO = new PaymentDAO();

    public static void main(String[] args) {

        int choice;
        do 
        {
            System.out.println("\n===== GYM MANAGER =====");
            System.out.println("1. Member Management");
            System.out.println("2. Workout Plan");
            System.out.println("3. Trainer Management");
            System.out.println("4. Payment Tracking");
            
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) 
            {
                case 1 -> memberMenu();
                case 2 -> workoutMenu();
                case 3 -> trainerMenu();
                case 4 -> paymentMenu();
                case 0 -> System.out.println("Thank you!");
                default -> System.out.println("Invalid choice");
            }
        } 
        while (choice != 0);
    }

    // ================= MEMBER =================
    static void memberMenu() {
        System.out.println("\n--- MEMBER MENU ---");
        System.out.println("1. Add Member");
        System.out.println("2. View Members");
        System.out.println("3. Search Member by ID");
        System.out.println("4. Search Member by Contact");
        System.out.println("5. View Members by Branch");
        System.out.println("6. Update Member");
        System.out.println("7. Delete Member");
        System.out.print("Choice: ");
        int ch = sc.nextInt();
        sc.nextLine();

        switch (ch) {
        
        case 1:
            try 
            {
                System.out.print("Name: ");
                String name = sc.nextLine();
                if (name.isEmpty()) 
                {
                    System.out.println("Name cannot be empty");
                    break;
                }

                System.out.print("Age: ");
                int age = sc.nextInt();
                if (age <= 0) 
                {
                    System.out.println("Age must be greater than 0");
                    break;
                }
                sc.nextLine();

                System.out.print("Contact: ");
                String contact = sc.nextLine();
                if (contact.isEmpty() || contact.length() < 10) 
                {
                    System.out.println("Invalid contact number");
                    break;
                }

                System.out.print("Plan Type (Basic/Gold/Platinum): ");
                String plan = sc.nextLine();
                if (!(plan.equalsIgnoreCase("Basic")
                        || plan.equalsIgnoreCase("Gold")
                        || plan.equalsIgnoreCase("Platinum"))) 
                {
                    System.out.println("Invalid plan type");
                    break;
                }

                System.out.print("Branch ID: ");
                int branchId = sc.nextInt();
                if (branchId <= 0) 
                {
                    System.out.println("Invalid branch ID");
                    break;
                }
                sc.nextLine();

                Member m1 = new Member(name, age, contact, plan, LocalDate.now());
                m1.setBranchId(branchId);
                memberDAO.addMember(m1);

                System.out.println("Member added successfully!");
            } 
            catch (java.util.InputMismatchException e) 
            {
                System.out.println("Invalid input type. Please enter correct values.");
                sc.nextLine(); // clear buffer
            }
            break;
            
        case 2:
            List<Member> list = memberDAO.getAllMembers();
            if (list.isEmpty())
            {
                System.out.println("No members found");
                break;
            }

            System.out.println("ID | Name | Age | Contact | Plan | Date");
            for (Member m : list) 
            {
                System.out.println(
                    m.getId() + " | " +
                    m.getName() + " | " +
                    m.getAge() + " | " +
                    m.getContact() + " | " +
                    m.getPlanType() + " | " +
                    m.getRegistrationDate()
                );
            }
            break;

        case 3:
            System.out.print("Enter Member ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            memberDAO.searchById(id);
            break;

        case 4:
            sc.nextLine(); // FIX for input issue
            System.out.print("Enter Contact: ");
            String c = sc.nextLine();
            memberDAO.searchByContact(c);
            break;

        case 5:
            System.out.print("Enter Branch ID: ");
            int bId = sc.nextInt();

            List<Member> blist = memberDAO.getMembersByBranchId(bId);
            if (blist.isEmpty())
            {
                System.out.println("No members found for this branch");
                break;
            }

            System.out.println("ID | Name | Age | Contact | Plan | Branch");
            for (Member m : blist) 
            {
                System.out.println(
                    m.getId() + " | " +
                    m.getName() + " | " +
                    m.getAge() + " | " +
                    m.getContact() + " | " +
                    m.getPlanType() + " | " +
                    m.getBranchId()
                );
            }
            break;

        case 6: // UPDATE
            System.out.print("Enter Member ID: ");
            int uid = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String uname = sc.nextLine();

            System.out.print("Enter Age: ");
            int uage = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Contact: ");
            String ucontact = sc.nextLine();

            System.out.print("Enter Plan Type: ");
            String uplan = sc.nextLine();

            System.out.print("Enter Branch ID: ");
            int ubr = sc.nextInt();

            Member m2 = new Member();
            m2.setId(uid);
            m2.setName(uname);
            m2.setAge(uage);
            m2.setContact(ucontact);
            m2.setPlanType(uplan);
            m2.setBranchId(ubr);

            memberDAO.updateMember(m2);
            break;

        case 7: // DELETE
            System.out.print("Enter Member ID to delete: ");
            int delId = sc.nextInt();
            memberDAO.deleteMember(delId);
            break;

        default:
            System.out.println("Invalid choice");
    }
  }
    
    // ================= WORKOUT =================
    static void workoutMenu() {
        System.out.println("\n--- WORKOUT MENU ---");
        System.out.println("1. Assign Workout");
        System.out.println("2. View Workout by Member");
        System.out.println("3. Update Workout Plan");
        System.out.print("Choice: ");
        int ch = sc.nextInt();

        switch (ch) 
        {
            case 1: 
            {
                System.out.print("Member ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Cardio: ");
                String c = sc.nextLine();
                System.out.print("Strength: ");
                String s = sc.nextLine();
                System.out.print("Flexibility: ");
                String f = sc.nextLine();

                workoutDAO.addWorkoutPlan(new WorkoutPlan(id, c, s, f));
            }

            case 2: 
            {
                System.out.print("Member ID: ");
                int id = sc.nextInt();
                List<WorkoutPlan> list = workoutDAO.getWorkoutByMemberId(id);

                if (list.isEmpty()) {
                    System.out.println("No workout found");
                    return;
                }

                for (WorkoutPlan w : list) {
                    System.out.println("Cardio: " + w.getCardio());
                    System.out.println("Strength: " + w.getStrength());
                    System.out.println("Flexibility: " + w.getFlexibility());
                }
            }
            
            case 3:
                System.out.print("Member ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("New Cardio: ");
                String c = sc.nextLine();

                System.out.print("New Strength: ");
                String s = sc.nextLine();

                System.out.print("New Flexibility: ");
                String f = sc.nextLine();

                WorkoutPlan w = new WorkoutPlan(id, c, s, f);
                workoutDAO.updateWorkoutPlanByMemberId(w);
                break;
        }
    }

    // ================= TRAINER =================
    static void trainerMenu() {

        System.out.println("\n--- TRAINER MENU ---");
        System.out.println("1. Add Trainer");
        System.out.println("2. View Trainers");
        System.out.println("3. Assign Trainer to Member");
        System.out.print("Choice: ");

        int ch = sc.nextInt();
        sc.nextLine();

        switch (ch) {

            case 1:
                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Specialty: ");
                String sp = sc.nextLine();

                System.out.print("Schedule: ");
                String sch = sc.nextLine();

                trainerDAO.addTrainer(new Trainer(name, sp, sch));
                break;

            case 2:
                List<Trainer> list = trainerDAO.getAllTrainers();
                for (Trainer t : list) 
                {
                    System.out.println(t.getTrainerId() + " | " +
                                       t.getName() + " | " +
                                       t.getSpecialty() + " | " +
                                       t.getSchedule());
                }
                break;

            case 3:
                System.out.print("Member ID: ");
                int mid = sc.nextInt();
                sc.nextLine();

                System.out.print("Member Plan Type: ");
                String plan = sc.nextLine();

                trainerDAO.assignTrainerToMember(mid, plan);
                break;
        }
    }
    
    // ================= PAYMENT =================
    static void paymentMenu() {

        try 
        {
            System.out.println("\n--- PAYMENT MENU ---");

            System.out.print("Enter Member ID: ");
            int memberId = sc.nextInt();
            if (memberId <= 0) 
            {
                System.out.println("Invalid Member ID");
                return;
            }
            sc.nextLine();

            System.out.print("Enter Plan Type (Basic/Gold/Platinum): ");
            String planType = sc.nextLine();

            if (!(planType.equalsIgnoreCase("Basic")
                    || planType.equalsIgnoreCase("Gold")
                    || planType.equalsIgnoreCase("Platinum"))) 
            {
                System.out.println("Invalid plan type");
                return;
            }

            System.out.print("Trainer Assigned? (yes/no): ");
            String input = sc.nextLine();

            boolean hasTrainer;
            if (input.equalsIgnoreCase("yes")) 
            {
                hasTrainer = true;
            } else if (input.equalsIgnoreCase("no")) 
            {
                hasTrainer = false;
            } else {
                System.out.println("Please enter only yes or no");
                return;
            }

            paymentDAO.generatePayment(memberId, planType, hasTrainer);

        } 
        catch (java.util.InputMismatchException e) 
        {
            System.out.println("Wrong input format! Please try again.");
            sc.nextLine(); // clear buffer
        }
    }
}
