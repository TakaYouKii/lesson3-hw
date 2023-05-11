import java.util.Scanner;

public class BankAccount {
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void deposit(double som){
        amount+=som;
    }

    public void withDraw(int som) throws LimitedException {
        LimitedException limitedException = new LimitedException("На вашем счету мало средств. Счет: "+ amount, amount);
        if(amount < som){
            throw limitedException;
        }
        amount-=som;
    }

    public BankAccount(double amount) {
        this.amount = amount;
    }


    public static String exitOrContinue(){
        Scanner input = new Scanner(System.in);
        System.out.println("Действие успешно выполнено" +
                "\n 1) Продолжить" +
                "\n 2) Выход " +
                "\n ВЫБЕРИТЕ ЦИФРУ ");
        while (true){
            int answer2 = input.nextInt();

            switch (answer2){
                case 1:
                    return "Continue";
                case 2:
                    return "Exit";
                default:
                    System.out.println("1 или 2");
            }

        }

    }
    public void BankAccountManager(){
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("Какое действие вы хотите совершить?" +
                    "\n 1)Посмотреть счет" +
                    "\n 2)Снять деньги со счета" +
                    "\n 3)Положить деньги на счет" +
                    "\n ВВЕДИТЕ ЦИФРУ");
            int action = input.nextInt();
            switch (action){
                case 1:
                    System.out.println("На вашет счету: " + this.getAmount());
                    break;
                case 2:
                    if(this.getAmount() == 0){
                        System.out.println("Нет денег на счету");
                        break;
                    } else if (this.getAmount() > 0) {
                        System.out.println("Введите сумму снятия: ");
                        int moneyToWithdraw = input.nextInt();
                        try {
                            this.withDraw(moneyToWithdraw);
                        } catch (LimitedException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Хотите снять отставшиеся " + e.getRemainingAmount() + "som ?");
                            while (true){
                                System.out.println("Да или Нет?");
                                String answer = input.next();
                                if(answer.equals("Да")){
                                    this.amount -= e.getRemainingAmount();
                                    break;
                                } else if (answer.equals("Нет")) {
                                    System.out.println("Действие снятия денег со счета отменено");
                                    break;
                                }
                            }
                        }

                    }
                    break;
                case 3:
                    System.out.println("Какую сумму хотите положить на счет?");
                    double moneyToDeposit = input.nextDouble();
                    this.deposit(moneyToDeposit);
                    break;


                default:
                    System.out.println("1, 2 или 3 !!!!" );
            }
            String answerExOrCont3 = exitOrContinue();
            if(answerExOrCont3.equals("Continue")){
                BankAccountManager();
            }else{
                System.out.println("Действие с банк вккаунтом завершены ");
                break;
            }

        }
    }
}
