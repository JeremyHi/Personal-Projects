package math;

public class BFIntTest {
    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {

        test_bFIntConstructor();
        test_equals();
        test_isGreaterThan();
        test_isLessThan();
        test_abs();
        test_plus();
        test_minus();
        test_times();
        test_dividedBy();
        test_mod();
        test_toString();

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value, int number) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(number + (number < 10 ? ".  " : ". ") +
                (value ? "success" : "failure"));
    }

    private static void displayFailure(int testNumber) {
        displaySuccessIfTrue(false, testNumber++);
    }

    public static void test_bFIntConstructor() {
        System.out.println("Testing BFInt Constructors...");
        int testNumber = 1;

        try {
            BFInt number = new BFInt();
            displaySuccessIfTrue(!number.isNegative(), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            BFInt number = new BFInt(Integer.MAX_VALUE);
            displaySuccessIfTrue(!number.isNegative(), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            BFInt number = new BFInt(-201);
            displaySuccessIfTrue(number.isNegative(), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            BFInt number = new BFInt(Long.MIN_VALUE);
            displaySuccessIfTrue(number.isNegative(), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            BFInt number = new BFInt(Byte.MIN_VALUE);
            displaySuccessIfTrue(number.isNegative(), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            BFInt number = new BFInt(Short.MAX_VALUE);
            displaySuccessIfTrue(!number.isNegative(), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            BFInt number = new BFInt("202231232131231232131231999999988231");
            displaySuccessIfTrue(!number.isNegative(), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            BFInt number = new BFInt("-202231232131231232131231999999988231");
            displaySuccessIfTrue(number.isNegative(), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            BFInt number = new BFInt("999999999883414231421392392923355");
            BFInt newNumber = new BFInt(number);
            displaySuccessIfTrue(!newNumber.isNegative(), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            BFInt bad = new BFInt("FDSBA");
            displayFailure(testNumber++);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true, testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            BFInt bad = new BFInt("1000.0");
            displayFailure(testNumber++);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true, testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            BFInt bad = new BFInt("1000E0");
            displayFailure(testNumber++);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true, testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        System.out.println();
    }

    public static void test_equals() {
        System.out.println("Testing equals...");
        int testNumber = 1;

        try {
            displaySuccessIfTrue(new BFInt("0").equals(BFInt.ZERO) &&
                    new BFInt().equals(BFInt.ZERO), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("10").equals(BFInt.TEN), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("-1").equals(BFInt.NEGATIVE_ONE),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("1").equals(BFInt.ONE), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("-1012").equals(new BFInt("-1012")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("1122332323231231231319999982321333222")
                    .equals(new BFInt("1122332323231231231319999982321333222")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("-1012").equals(new BFInt("1012")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("1022").equals(new BFInt("1012")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("99890").equals(new BFInt("9989")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("00099890").equals(new BFInt("99890")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("+099890").equals(new BFInt("99890")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("-72112").equals(new BFInt("-72113")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("-02").equals(new BFInt("+02")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        System.out.println();
    }

    public static void test_isGreaterThan() {
        System.out.println("Testing isGreaterThan...");
        int testNumber = 1;

        try {
            displaySuccessIfTrue(new BFInt("5001").isGreaterThan(new BFInt("28")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("333").isGreaterThan(new BFInt("12121")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }
        //test 3
        try {
            displaySuccessIfTrue(new BFInt("-77").isGreaterThan(new BFInt("-5666")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("-89999").isGreaterThan(new BFInt("-20")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("209").isGreaterThan(new BFInt("209")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("-40").isGreaterThan(new BFInt("-40")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("0").isGreaterThan(new BFInt("0")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }
        //test 8
        try {
            displaySuccessIfTrue(new BFInt("9877").isGreaterThan(new BFInt("9077")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }
        //test 9
        try {
            displaySuccessIfTrue(new BFInt("500574857392211333231233444319")
                    .isGreaterThan(new BFInt("500574857392211333231233444318")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }
        //test 10
        try {
            displaySuccessIfTrue(!new BFInt("63454253454325209989811891895619")
                    .isGreaterThan(new BFInt("63454253454325209989811891895620")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("-999877621003213202019293035971")
                    .isGreaterThan(new BFInt("-999877621003213202019293035970")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("-4554311120219383900484583214012334")
                    .isGreaterThan(new BFInt("-4554311120219383900484583214012335")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("-50").isGreaterThan(new BFInt("11")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("0").isGreaterThan(new BFInt("-1")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("0").isGreaterThan(new BFInt("1")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("120").isGreaterThan(new BFInt("108")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("108").isGreaterThan(new BFInt("120")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("-120").isGreaterThan(new BFInt("-108")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        System.out.println();
    }

    public static void test_isLessThan() {
        System.out.println("Testing isLessThan...");
        int testNumber = 1;

        try {
            displaySuccessIfTrue(new BFInt("0").isLessThan(new BFInt("1")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("908").isLessThan(new BFInt("908")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("-298").isLessThan(new BFInt("-298")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("0").isLessThan(new BFInt("-1")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("-9001").isLessThan(new BFInt("90")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("521").isLessThan(new BFInt("-50211")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("212").isLessThan(new BFInt("1111")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("599").isLessThan(new BFInt("7872")),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("59923243141112")
                    .isLessThan(new BFInt("59923243141113")), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("-34235792090909192")
                    .isLessThan(new BFInt("-34235792090909191")), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("989999999999999999")
                    .isLessThan(new BFInt("-998999999999999999")), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("-871321463246104932")
                    .isLessThan(new BFInt("871320463246104932")), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("8087")
                    .isLessThan(new BFInt("8187")), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("-4898")
                    .isLessThan(new BFInt("-4888")), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        System.out.println();
    }

    public static void test_plus() {
        System.out.println("Testing add...");
        int testNumber = 1;

        BFInt addend1 = new BFInt("0");
        BFInt addend2 = new BFInt("8");

        try {
            displaySuccessIfTrue(new BFInt("8").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("19");

        try {
            displaySuccessIfTrue(new BFInt("27").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("500124");
        addend2 = new BFInt("2892");

        try {
            displaySuccessIfTrue(new BFInt("503016").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("221");
        addend2 = new BFInt("690");

        try {
            displaySuccessIfTrue(new BFInt("911").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("812");
        addend2 = new BFInt("208");

        try {
            displaySuccessIfTrue(new BFInt("1020").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("6");
        addend2 = new BFInt("97");

        try {
            displaySuccessIfTrue(new BFInt("103").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("9");
        addend2 = new BFInt("502");

        try {
            displaySuccessIfTrue(new BFInt("511").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("999999");
        addend2 = new BFInt("2");

        try {
            displaySuccessIfTrue(new BFInt("1000001").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("6435325245234324324443239880");
        addend2 = new BFInt("43087430947319847310483150941");

        try {
            displaySuccessIfTrue(new BFInt("49522756192554171634926390821")
                    .equals(addend1.plus(addend2)), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("-15");
        addend2 = new BFInt("-13");

        try {
            displaySuccessIfTrue(new BFInt("-28").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("-1");
        addend2 = new BFInt("-13");
        //test 11
        try {
            displaySuccessIfTrue(new BFInt("-14").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("-2300");
        addend2 = new BFInt("-109");

        try {
            displaySuccessIfTrue(new BFInt("-2409").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("-587");
        addend2 = new BFInt("-503");

        try {
            displaySuccessIfTrue(new BFInt("-1090").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("202");
        addend2 = new BFInt("-101");

        try {
            displaySuccessIfTrue(new BFInt("101").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("100");
        addend2 = new BFInt("-84");
        //test 15
        try {
            displaySuccessIfTrue(new BFInt("16").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("-603");
        addend2 = new BFInt("542");
        //test 16
        try {
            displaySuccessIfTrue(new BFInt("-61").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("74");
        addend2 = new BFInt("-74");

        try {
            displaySuccessIfTrue(new BFInt("0").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        addend1 = new BFInt("67");
        addend2 = new BFInt("-89");
        //test 18
        try {
            displaySuccessIfTrue(new BFInt("-22").equals(addend1.plus(addend2)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        System.out.println();
    }

    public static void test_minus() {
        System.out.println("Testing minus...");
        int testNumber = 1;

        BFInt minuend = new BFInt("552");
        BFInt subtrahend = new BFInt("440");

        try {
            displaySuccessIfTrue(new BFInt("112").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("1123");
        subtrahend = new BFInt("155");
        //test 2
        try {
            displaySuccessIfTrue(new BFInt("968").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("1000001");
        subtrahend = new BFInt("2");
        //test 3
        try {
            displaySuccessIfTrue(new BFInt("999999").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("94");
        subtrahend = new BFInt("94");

        try {
            displaySuccessIfTrue(new BFInt("0").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("28");
        subtrahend = new BFInt("99");
        //test 5
        try {
            displaySuccessIfTrue(new BFInt("-71").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }
        
        minuend = new BFInt("7632");
        subtrahend = new BFInt("11111");
        //test 6
        try {
            displaySuccessIfTrue(new BFInt("-3479").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("0");
        subtrahend = new BFInt("10099");
        //test 7
        try {
            displaySuccessIfTrue(new BFInt("-10099").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("162");
        subtrahend = new BFInt("-34");
        //test 8
        try {
            displaySuccessIfTrue(new BFInt("196").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("0");
        subtrahend = new BFInt("-722");
        //test 9
        try {
            displaySuccessIfTrue(new BFInt("722").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("-12");
        subtrahend = new BFInt("-24");
        //test 10
        try {
            displaySuccessIfTrue(new BFInt("12").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("-99887743");
        subtrahend = new BFInt("-99887743");

        try {
            displaySuccessIfTrue(new BFInt("0").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("-761");
        subtrahend = new BFInt("-82");
        //test 12
        try {
            displaySuccessIfTrue(new BFInt("-679").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("-81");
        subtrahend = new BFInt("-77");
        //test 13
        try {
            displaySuccessIfTrue(new BFInt("-4").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("-82");
        subtrahend = new BFInt("-200");
        //test 14
        try {
            displaySuccessIfTrue(new BFInt("118").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("-11");
        subtrahend = new BFInt("50");
        //test 15
        try {
            displaySuccessIfTrue(new BFInt("-61").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        minuend = new BFInt("-8");
        subtrahend = new BFInt("313");
        //test 16
        try {
            displaySuccessIfTrue(new BFInt("-321").equals(minuend.minus(subtrahend)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        System.out.println();
    }

    public static void test_times() {
        System.out.println("Testing times...");
        int testNumber = 1;

        BFInt multiplicand = new BFInt("21");
        BFInt multiplier = new BFInt("4");
        //test 1
        try {
            displaySuccessIfTrue(new BFInt("84").equals(multiplicand.times(multiplier)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        multiplicand = new BFInt("38");
        multiplier = new BFInt("5");
        //test 2
        try {
            displaySuccessIfTrue(new BFInt("190").equals(multiplicand.times(multiplier)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        multiplicand = new BFInt("454");
        multiplier = new BFInt("102349");
        //test 3
        try {
            displaySuccessIfTrue(new BFInt("46466446").equals(multiplicand.times(multiplier)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        multiplicand = new BFInt("-20");
        multiplier = new BFInt("988");
        //test 4
        try {
            displaySuccessIfTrue(new BFInt("-19760").equals(multiplicand.times(multiplier)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        multiplicand = new BFInt("-10099");
        multiplier = new BFInt("-2");
        //test 5
        try {
            displaySuccessIfTrue(new BFInt("20198").equals(multiplicand.times(multiplier)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }
        
        multiplicand = new BFInt("435346546546547391231091910099");
        multiplier = new BFInt("0");
        //test 6
        try {
            displaySuccessIfTrue(new BFInt("0").equals(multiplicand.times(multiplier)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        multiplicand = new BFInt("0");
        multiplier = new BFInt("-50000000000001");
        //test 7
        try {
            displaySuccessIfTrue(new BFInt("0").equals(multiplicand.times(multiplier)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        multiplicand = new BFInt("202");
        multiplier = new BFInt("651");
        //test 8
        try {
            displaySuccessIfTrue(new BFInt("131502").equals(multiplicand.times(multiplier)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        multiplicand = new BFInt("2010");
        multiplier = new BFInt("7000000000");
        //test 9
        try {
            displaySuccessIfTrue(new BFInt("14070000000000").equals(multiplicand.times(multiplier)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        multiplicand = new BFInt("-1");
        multiplier = new BFInt("1984739146319471042831093212343141");
        //test 10
        try {
            displaySuccessIfTrue(new BFInt("-1984739146319471042831093212343141")
                    .equals(multiplicand.times(multiplier)),
                            testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        multiplicand = new BFInt("-98274090");
        multiplier = new BFInt("-223090000");
        //test 11
        try {
            displaySuccessIfTrue(new BFInt("21923966738100000")
                    .equals(multiplicand.times(multiplier)),
                            testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        multiplicand = new BFInt("-2");
        multiplier = new BFInt("4");
        //test 12
        try {
            displaySuccessIfTrue(new BFInt("-8").equals(multiplicand.times(multiplier)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        multiplicand = new BFInt("9999");
        multiplier = new BFInt("99");
        //test 13
        try {
            displaySuccessIfTrue(new BFInt("989901").equals(multiplicand.times(multiplier)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        System.out.println();
    }

    public static void test_dividedBy() {
        System.out.println("Testing dividedBy...");
        int testNumber = 1;

        BFInt dividend = new BFInt("9");
        BFInt divisor = new BFInt("3");
        //test 1
        try {
            displaySuccessIfTrue(new BFInt("3").equals(dividend.dividedBy(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }
        
        dividend = new BFInt("108");
        divisor = new BFInt("12");
        //test 2
        try {
            displaySuccessIfTrue(new BFInt("9").equals(dividend.dividedBy(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("-1203");
        divisor = new BFInt("32");
        //test 3
        try {
            displaySuccessIfTrue(new BFInt("-37").equals(dividend.dividedBy(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("-15029");
        divisor = new BFInt("-19");
        //test 4
        try {
            displaySuccessIfTrue(new BFInt("791").equals(dividend.dividedBy(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("5023");
        divisor = new BFInt("8");

        try {
            displaySuccessIfTrue(new BFInt("627").equals(dividend.dividedBy(divisor)),
            testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("-234314109990900912");
        divisor = new BFInt("-234314109990900912");
        //test 6
        try {
            displaySuccessIfTrue(new BFInt("1").equals(dividend.dividedBy(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("-521342132431444421419464638756546331");
        divisor = new BFInt("521342132431444421419464638756546331");
        //test 7
        try {
            displaySuccessIfTrue(new BFInt("-1").equals(dividend.dividedBy(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("9093918430176447128");
        divisor = new BFInt("-23432191");
        //test 8
        try {
            displaySuccessIfTrue(new BFInt("-388095096620").equals(

                    dividend.dividedBy(divisor)), testNumber++);

        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("12");
        divisor = new BFInt("14");
        //test 9
        try {
            displaySuccessIfTrue(new BFInt("0").equals(dividend.dividedBy(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("-5");
        divisor = new BFInt("100");
        //test 10
        try {
            displaySuccessIfTrue(new BFInt("0").equals(dividend.dividedBy(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("102121434314130989988989");
        divisor = new BFInt("-398568195791683984612964");

        try {
            displaySuccessIfTrue(new BFInt("0").equals(dividend.dividedBy(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("-10001");
        divisor = new BFInt("0");
        //test 12
        try {
            BFInt remainder = dividend.dividedBy(divisor);
            displayFailure(testNumber++);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true, testNumber);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }
        

        System.out.println();
    }

    public static void test_mod() {
        System.out.println("Testing mod...");
        int testNumber = 1;

        BFInt dividend = new BFInt("32");
        BFInt divisor = new BFInt("5");

        try {
            displaySuccessIfTrue(new BFInt("2").equals(dividend.mod(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("523150");
        divisor = new BFInt("523150");

        try {
            displaySuccessIfTrue(new BFInt("0").equals(dividend.mod(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("987");
        divisor = new BFInt("1000");

        try {
            displaySuccessIfTrue(new BFInt("987").equals(dividend.mod(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("354324393857012089941");
        divisor = new BFInt("23244559");

        try {
            displaySuccessIfTrue(new BFInt("835082").equals(dividend.mod(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("29");
        divisor = new BFInt("-10");
        //test 5
        try {
            displaySuccessIfTrue(new BFInt("9").equals(dividend.mod(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("-120");
        divisor = new BFInt("7");
        //test 6
        try {
            displaySuccessIfTrue(new BFInt("-1").equals(dividend.mod(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("-1054");
        divisor = new BFInt("-28");
        //test 7
        try {
            displaySuccessIfTrue(new BFInt("-18").equals(dividend.mod(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("-800");
        divisor = new BFInt("-800");

        try {
            displaySuccessIfTrue(new BFInt("0").equals(dividend.mod(divisor)),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        dividend = new BFInt("720");
        divisor = new BFInt("0");

        try {
            BFInt remainder = dividend.mod(divisor);
            displayFailure(testNumber++);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true, testNumber);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        System.out.println();
    }

    public static void test_toString() {
        System.out.println("Testing toString...");
        int testNumber = 1;

        try {
            displaySuccessIfTrue("0".equals(BFInt.ZERO.toString()), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue("-900".equals(new BFInt("-900").toString()),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue("198234243214321434313431431241324887696969910001"
                    .equals(new BFInt("198234243214321434313431431241324887696969910001")
                            .toString()), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue("-900".equals(new BFInt("-900").toString()),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!"900".equals(new BFInt("-900").toString()),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue("575".equals(new BFInt("+575").toString()),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue("2000".equals(new BFInt("0002000").toString()),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        System.out.println();
    }

    public static void test_abs() {
        System.out.println("Testing abs...");
        int testNumber = 1;

        try {
            displaySuccessIfTrue(new BFInt("2000").equals(new BFInt("2000").abs()),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(new BFInt("123123444677659090989875681231")
                    .equals(new BFInt("-123123444677659090989875681231").abs()),
                            testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(BFInt.ZERO.equals(BFInt.ZERO.abs()), testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("-985").equals(new BFInt("985").abs()),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        try {
            displaySuccessIfTrue(!new BFInt("-222").equals(new BFInt("-222").abs()),
                    testNumber++);
        } catch (Exception e) {
            displayFailure(testNumber++);
            e.printStackTrace();
        }

        System.out.println();
    }

}