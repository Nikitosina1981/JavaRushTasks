public class Test {
            public static void main(String[] args)  {
                String s = "Hello";
                String s1 = "Hello";
                String s2 = new String("Hello");

                System.out.println(s == s1);
                System.out.println(s1 == s2);

                Integer i = 111;
                Integer i1 = 111;
                Integer i2 = new Integer(111);
                Integer i3 = new Integer(111);

                Integer i4 = -129;
                Integer i5 = -129;
                Integer i6 = new Integer(-129);

                System.out.println(i == i1);
                System.out.println(i1 == i2);
                System.out.println(i2 == i3);

                System.out.println(i4 == i5);
                System.out.println(i5 == i6);

                System.out.println(i6 == -129);
            }
        }

