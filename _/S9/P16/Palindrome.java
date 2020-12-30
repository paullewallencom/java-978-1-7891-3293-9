public class Palindrome {

    public static void main(String[] args) {
        System.out.println(String.format("Is %s a palindrome? : %s", "Loonycorn", isPalindrome("Loonycorn")));
        System.out.println(String.format("Is %s a palindrome? : %s", "deleveled", isPalindrome("deleveled")));
        System.out.println(String.format("Is %s a palindrome? : %s", "refer", isPalindrome("refer")));

        System.out.println(String.format("Is %s a palindrome? : %s", "Malayalam", isPalindrome("Malayalam")));
        System.out.println(String.format("Is %s a palindrome? : %s", "Never   odd   or   even",
                isPalindrome("Never   odd   or   even")));
        System.out.println(String.format("Is %s a palindrome? : %s", "Lonely Tylenol",
                isPalindrome("Lonely Tylenol")));
    }

    public static boolean isPalindrome(String testString) {
        testString = testString.toLowerCase();

        int index = 0;
        int lastIndex = testString.length() - 1;

        while (index < lastIndex) {
            char forwardChar = testString.charAt(index);
            char reverseChar = testString.charAt(lastIndex);
            while (forwardChar == ' ') {
                index++;
                forwardChar = testString.charAt(index);
            }
            while (reverseChar == ' ') {
                lastIndex--;
                reverseChar = testString.charAt(lastIndex);
            }
            if (forwardChar != reverseChar) {
                return false;
            }
            index++;
            lastIndex--;
        }

        return true;
    }
}
