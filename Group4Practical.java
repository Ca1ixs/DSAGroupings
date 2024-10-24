import java.util.*;

class Employee {
    String employeeID;
    String employeeName;
    String department;
    String rank;
    double grossPay;
    double totalHoursPerMonth;
    double hourlyRate;
    double absences;
    double netPay;
    double withHoldingTax;
    double totalDeductions;

    public Employee(String employeeID, String employeeName, String department, String rank,
                    double grossPay, double totalHoursPerMonth, double hourlyRate, double absences) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.department = department;
        this.rank = rank;
        this.grossPay = grossPay;
        this.totalHoursPerMonth = totalHoursPerMonth;
        this.hourlyRate = hourlyRate;
        this.absences = absences;
        calculateNetPay();
    }

    private void calculateNetPay() {
        totalDeductions = absences * hourlyRate;
        double taxableIncome = grossPay - totalDeductions;  
        withHoldingTax = taxableIncome * 0.25;  
        this.netPay = taxableIncome - withHoldingTax; 
    }

    public void displayEmployeeInfo() {
        System.out.println("Employee ID: " + employeeID);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Department: " + department);
        System.out.println("Rank: " + rank);
        System.out.printf("Gross Pay: P" +"%.2f%n", grossPay);
        System.out.println("Total Hours per Month: " + totalHoursPerMonth + " Hours");
        System.out.printf("Hourly Rate: P" + "%.2f%n",hourlyRate);
        System.out.println("Absences: " + absences + " Hours");
        System.out.printf("Total Deductions: P" +"%.2f%n",totalDeductions);
        System.out.printf("Withholding Tax: P" +"%.2f%n",withHoldingTax);
    	System.out.println("\n======================================");
        System.out.printf("       Net Pay: P" +"%.2f%n", netPay);
    }
}

public class Group4Practical {
    public static void main(String[] args) {
        LinkedList<Employee> EmployeeList = new LinkedList<>();
        Scanner input = new Scanner(System.in);
        int choice = 0;
        double grossPay = 0;
        boolean checker = false, rankChecker = false;
        int counter = 0;
        String rank;

        System.out.println("======================================");
        System.out.println("     Calculate Employee Payslip");
        System.out.println("======================================");

        do {
            try {
                System.out.println("(1): Display Employees Payslip");
                System.out.println("(2): Add Employee Payslip");
                System.out.println("(3): Exit");
                System.out.print("Enter your choice: ");
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("======================================");
                        System.out.println("           Employee Payslip");
                        System.out.println("======================================");

                        if (EmployeeList.isEmpty()) {
                            System.out.println("          No employees found.");
                            System.out.println("======================================");
                        } else {
                            for (Employee employee : EmployeeList) {
                                employee.displayEmployeeInfo();
                                System.out.println("======================================");
                            }
                        }
                        break;

                    case 2:
                        System.out.println("======================================");
                        System.out.println("        Employee Information");
                        System.out.println("======================================");
                        System.out.print("Enter Employee ID: ");
                        String employeeID = input.nextLine();
                        System.out.println("--------------------------------------");
                        System.out.print("Enter Employee Name: ");
                        String employeeName = input.nextLine();
                        System.out.println("--------------------------------------");
                        System.out.print("Enter Department: ");
                        String department = input.nextLine();
                        System.out.println("--------------------------------------");

                        int rankCounter = 0;
                        rankChecker = false;
                        
                        do {
                            System.out.println("(CEO, Manager, Supervisor, RankAndFile)");
                            System.out.print("Enter Employee Rank: ");
                            rank = input.nextLine();

                            if (rank.equalsIgnoreCase("ceo") ||
                                rank.equalsIgnoreCase("manager") ||
                                rank.equalsIgnoreCase("supervisor") ||
                                rank.equalsIgnoreCase("rankandfile")) {
                                rankChecker = true;
                            } else {
                                rankCounter++;
                                System.out.println("Invalid rank. Please try again.");

                                if (rankCounter >= 3) {
                                    System.out.println("======================================");
                                    System.out.println("Reached Max Attempts...");
                                    break;
                                }
                            }

                        } while (!rankChecker && rankCounter < 3);

                        if (rankCounter >= 3) {
                            System.out.println("Returning to main menu...");
                            System.out.println("======================================");
                            continue; 
                        }

                        if (rankChecker) {
                            counter = 0;

                            do {
                                checker = true;
                                switch (rank.toUpperCase()) {
                                    case "CEO":
                                        System.out.println("--------------------------------------");
                                        System.out.println("Gross Pay Range: P70,000 - P100,000");
                                        break;

                                    case "MANAGER":
                                        System.out.println("--------------------------------------");
                                        System.out.println("Gross Pay Range: P50,000 - P70,000");
                                        break;

                                    case "SUPERVISOR":
                                        System.out.println("--------------------------------------");
                                        System.out.println("Gross Pay Range: P20,000 - P50,000");
                                        break;

                                    case "RANKANDFILE":
                                        System.out.println("--------------------------------------");
                                        System.out.println("Gross Pay Range: P10,000 - P20,000");
                                        break;

                                    default:
                                        System.out.println("Invalid rank entered. Please enter a valid rank.");
                                        counter = 3;
                                        checker = false;
                                        break;
                                }

                                System.out.print("Enter Gross Pay: ");
                                try {
                                    grossPay = input.nextDouble();
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid number for gross pay.");
                                    input.nextLine();
                                    counter++;
                                    continue; 
                                }

                                switch (rank.toUpperCase()) {
                                    case "CEO":
                                        if (grossPay < 70000 || grossPay > 100000) {
                                            System.out.println("--------------------------------------");
                                            System.out.println("Please enter an amount in the specific range (P70,000 - P100,000).");
                                            counter++;
                                        } else {
                                            checker = false; 
                                        }
                                        break;

                                    case "MANAGER":
                                        if (grossPay < 50000 || grossPay > 70000) {
                                            System.out.println("--------------------------------------");
                                            System.out.println("Please enter an amount in the specific range (P50,000 - P70,000).");
                                            counter++;
                                        } else {
                                            checker = false;
                                        }
                                        break;

                                    case "SUPERVISOR":
                                        if (grossPay < 20000 || grossPay > 50000) {
                                            System.out.println("--------------------------------------");
                                            System.out.println("Please enter an amount in the specific range (P20,000 - P50,000).");
                                            counter++;
                                        } else {
                                            checker = false;
                                        }
                                        break;

                                    case "RANKANDFILE":
                                        if (grossPay < 10000 || grossPay > 20000) {
                                            System.out.println("--------------------------------------");
                                            System.out.println("Please enter an amount in the specific range (P10,000 - P20,000).");
                                            counter++;
                                        } else {
                                            checker = false;
                                        }
                                        break;
                                }

                                if (counter >= 3) {
                                    System.out.println("======================================");
                                    System.out.println("Reached Max Attempts...");
                                    System.out.println("Returning to main menu...");
                                    System.out.println("======================================");
                                    checker = false;
                                }

                            } while (checker);

                            if (counter < 3) {
                                try {
                                    System.out.println("--------------------------------------");
                                    System.out.print("Enter Total Hours per Month: ");
                                    double totalHoursPerMonth = input.nextDouble();
                                    System.out.println("--------------------------------------");
                                    System.out.print("Enter Hourly Rate: ");
                                    double hourlyRate = input.nextDouble();
                                    System.out.println("--------------------------------------");
                                    System.out.print("Enter Tardiness/Undertime/Absences (in hours): ");
                                    double absences = input.nextDouble();
                                    System.out.println("--------------------------------------");

                                    Employee newEmployee = new Employee(employeeID, employeeName, department, rank.toUpperCase(), grossPay, totalHoursPerMonth, hourlyRate, absences);
                                    EmployeeList.add(newEmployee);
                                } catch (InputMismatchException e) {
                                    System.out.println("======================================");
                                    System.out.println("Invalid input. Please enter numbers only.");
                                    System.out.println("======================================");
                                    input.nextLine(); 
                                }
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Program Terminated...");
                        System.out.println("======================================");
                        input.close();
                        return;

                    default:
                        System.out.println("Invalid input, Please enter the corresponding number.");
                        System.out.println("======================================");
                }
            } catch (InputMismatchException e) {
                System.out.println("======================================");
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.println("======================================");
                input.nextLine(); 
            }
        } while (choice != 3);
    }
}