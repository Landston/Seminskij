package main.Task3.First;


import java.util.Random;

public class main {

    public static void main(String[] args) {

         maxCharacterInRandomNumber();

        sumOfFirstCharactersOfThreeNumbers();


    }

    public static void sumOfFirstCharactersOfThreeNumbers() {


        int sum = 0;

        Boolean flag = false;

        int count = 0;

        while (!flag) {

            int number = 100 + (new java.util.Random().nextInt(1000 - 100 + 1));


            if (number >= 100) {

                System.out.println(number);

                count += 1;

                sum += number / 100;


                if (count == 3) flag = true;
            }


        }

        System.out.println(sum);
    }

    public static void maxCharacterInRandomNumber() {

        int number = 0;

        Boolean flag = false;

        int max = 0;


        number = 100 + (new java.util.Random().nextInt(1000 - 100 ));

        System.out.println(number);



        for(int i = 1 ; i< 4; i++){

            int num =(int)((number%(Math.pow(10,i)))/Math.pow(10,i-1));


            if(num > max)
            {

            max = num;

        }
        }


        System.out.println("Max " + max);

    }
}
