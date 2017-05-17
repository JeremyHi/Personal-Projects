import java.io.*;
import java.util.*;

public class 1WordFrequencies {
    public 1WordFrequencies(){

    }

    public static void main(String args[]) {
        try {
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            HashMap hashmap;
            String s;
            boolean flag3;
            BufferedReader bufferedreader;
            String s1;
            switch(args.length) {
                default:
                    throw new IllegalArgumentException();
                case 1: // '\001'
                    if(args[0].charAt(0) != '-')
                        throw new IllegalArgumentException();
                    for(int i = 1; i < args[0].length(); i++)
                        switch(args[0].charAt(i)) {
                        case 97: // 'a'
                            flag = true;
                            break;
                        case 99: // 'c'
                            flag1 = true;
                            break;
                        case 115: // 's'
                            flag2 = true;
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                    // fall through
                case 0: // '\0'
                    hashmap = new HashMap();
                    s = null;
                    flag3 = false;
                    bufferedreader = new BufferedReader(new InputStreamReader(System.in));
                    s1 = bufferedreader.readLine();
                    System.out.println(s1);
                    break;
                }
            for(; s1 != null; s1 = bufferedreader.readLine())
            {
                if(!flag3)
                {
                    s = "";
                    flag3 = false;
                }
                for(int j = 0; j < s1.length(); j++)
                    switch(s1.charAt(j))
                    {
                    case 39: // '\''
                        if(!flag)
                            break;
                        s = (new StringBuilder()).append(s).append(String.valueOf(s1.charAt(j))).toString();
                        if(j != s1.length() - 1)
                            break;
                        Object obj1 = hashmap.get(s);
                        if(obj1 == null)
                            hashmap.put(s, new Integer(1));
                        else
                            hashmap.put(s, new Integer(1 + ((Integer)obj1).intValue()));
                        s = "";
                        break;

                    case 45: // '-'
                        flag3 = j == s1.length() - 1;
                        break;

                    case 48: // '0'
                    case 49: // '1'
                    case 50: // '2'
                    case 51: // '3'
                    case 52: // '4'
                    case 53: // '5'
                    case 54: // '6'
                    case 55: // '7'
                    case 56: // '8'
                    case 57: // '9'
                    case 65: // 'A'
                    case 66: // 'B'
                    case 67: // 'C'
                    case 68: // 'D'
                    case 69: // 'E'
                    case 70: // 'F'
                    case 71: // 'G'
                    case 72: // 'H'
                    case 73: // 'I'
                    case 74: // 'J'
                    case 75: // 'K'
                    case 76: // 'L'
                    case 77: // 'M'
                    case 78: // 'N'
                    case 79: // 'O'
                    case 80: // 'P'
                    case 81: // 'Q'
                    case 82: // 'R'
                    case 83: // 'S'
                    case 84: // 'T'
                    case 85: // 'U'
                    case 86: // 'V'
                    case 87: // 'W'
                    case 88: // 'X'
                    case 89: // 'Y'
                    case 90: // 'Z'
                        s = (new StringBuilder()).append(s).append(String.valueOf(s1.charAt(j))).toString();
                        if(j != s1.length() - 1)
                            break;
                        Object obj2 = hashmap.get(s);
                        if(obj2 == null)
                            hashmap.put(s, new Integer(1));
                        else
                            hashmap.put(s, new Integer(1 + ((Integer)obj2).intValue()));
                        s = "";
                        break;

                    case 97: // 'a'
                    case 98: // 'b'
                    case 99: // 'c'
                    case 100: // 'd'
                    case 101: // 'e'
                    case 102: // 'f'
                    case 103: // 'g'
                    case 104: // 'h'
                    case 105: // 'i'
                    case 106: // 'j'
                    case 107: // 'k'
                    case 108: // 'l'
                    case 109: // 'm'
                    case 110: // 'n'
                    case 111: // 'o'
                    case 112: // 'p'
                    case 113: // 'q'
                    case 114: // 'r'
                    case 115: // 's'
                    case 116: // 't'
                    case 117: // 'u'
                    case 118: // 'v'
                    case 119: // 'w'
                    case 120: // 'x'
                    case 121: // 'y'
                    case 122: // 'z'
                        if(flag2)
                            s = (new StringBuilder()).append(s).append(String.valueOf(s1.charAt(j))).toString();
                        else
                            s = (new StringBuilder()).append(s).append(String.valueOf(toUpperCase(s1.charAt(j)))).toString();
                        if(j != s1.length() - 1)
                            break;
                        Object obj3 = hashmap.get(s);
                        if(obj3 == null)
                            hashmap.put(s, new Integer(1));
                        else
                            hashmap.put(s, new Integer(1 + ((Integer)obj3).intValue()));
                        s = "";
                        break;

                    case 40: // '('
                    case 41: // ')'
                    case 42: // '*'
                    case 43: // '+'
                    case 44: // ','
                    case 46: // '.'
                    case 47: // '/'
                    case 58: // ':'
                    case 59: // ';'
                    case 60: // '<'
                    case 61: // '='
                    case 62: // '>'
                    case 63: // '?'
                    case 64: // '@'
                    case 91: // '['
                    case 92: // '\\'
                    case 93: // ']'
                    case 94: // '^'
                    case 95: // '_'
                    case 96: // '`'
                    default:
                        if(s.length() <= 0)
                            break;
                        Object obj4 = hashmap.get(s);
                        if(obj4 == null)
                            hashmap.put(s, new Integer(1));
                        else
                            hashmap.put(s, new Integer(1 + ((Integer)obj4).intValue()));
                        s = "";
                        break;
                    }

                if(s.length() <= 0 || flag3)
                    continue;
                Object obj = hashmap.get(s);
                if(obj == null)
                    hashmap.put(s, new Integer(1));
                else
                    hashmap.put(s, new Integer(1 + ((Integer)obj).intValue()));
                s = "";
            }

            if(flag1)
                outputWithoutFrequencies(hashmap);
            else
                outputWithFrequencies(hashmap);
        }
        catch(Exception exception)
        {
            System.out.println(exception.getMessage());
            System.out.println("Usage: \tjava InventoryOfWords2 [-acs]\n");
        }
    }

    private static char toUpperCase(char c)
    {
        switch(c)
        {
        case 97: // 'a'
            return 'A';

        case 98: // 'b'
            return 'B';

        case 99: // 'c'
            return 'C';

        case 100: // 'd'
            return 'D';

        case 101: // 'e'
            return 'E';

        case 102: // 'f'
            return 'F';

        case 103: // 'g'
            return 'G';

        case 104: // 'h'
            return 'H';

        case 105: // 'i'
            return 'I';

        case 106: // 'j'
            return 'J';

        case 107: // 'k'
            return 'K';

        case 108: // 'l'
            return 'L';

        case 109: // 'm'
            return 'M';

        case 110: // 'n'
            return 'N';

        case 111: // 'o'
            return 'O';

        case 112: // 'p'
            return 'P';

        case 113: // 'q'
            return 'Q';

        case 114: // 'r'
            return 'R';

        case 115: // 's'
            return 'S';

        case 116: // 't'
            return 'T';

        case 117: // 'u'
            return 'U';

        case 118: // 'v'
            return 'V';

        case 119: // 'w'
            return 'W';

        case 120: // 'x'
            return 'X';

        case 121: // 'y'
            return 'Y';

        case 122: // 'z'
            return 'Z';
        }
        return c;
    }

    static void outputWithoutFrequencies(HashMap hashmap)
    {
        Set set = hashmap.keySet();
        ArrayList arraylist = new ArrayList();
        Object obj;
        for(Iterator iterator = set.iterator(); iterator.hasNext(); arraylist.add(obj.toString()))
            obj = iterator.next();

        Collections.sort(arraylist);
        String s;
        for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); System.out.println(s.toString()))
            s = (String)iterator1.next();

    }

    static void outputWithFrequencies(HashMap hashmap)
    {
        Set set = hashmap.keySet();
        ArrayList arraylist = new ArrayList();
        Object obj;
        for(Iterator iterator = set.iterator(); iterator.hasNext(); arraylist.add(obj.toString()))
            obj = iterator.next();

        Collections.sort(arraylist);
        String s;
        for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); System.out.println((new StringBuilder()).append(s.toString()).append(" ").append(((Integer)(Integer)hashmap.get(s)).toString()).toString()))
            s = (String)iterator1.next();

    }

    private static final char APOSTROPHE = 39;
    private static final char HYPHEN = 45;
}
