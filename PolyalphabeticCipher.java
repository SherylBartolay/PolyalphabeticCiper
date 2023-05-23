import java.io.*;

public class PolyalphabeticCipher {
    public static void main(String[] args) {
        String inputFilePath = "input.txt";
        String encodedFilePath = "encoded.txt";
        String decodedFilePath = "decoded.txt";

        String key = "SECRETKEY"; // Change this key as per your preference

        // Encode the input text file
        encodeTextFile(inputFilePath, encodedFilePath, key);
        System.out.println("File encoded successfully!");

        // Decode the encoded text file
        decodeTextFile(encodedFilePath, decodedFilePath, key);
        System.out.println("File decoded successfully!");
    }

    private static void encodeTextFile(String inputFilePath, String encodedFilePath, String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(encodedFilePath))) {

            int keyIndex = 0;
            int currentChar;

            while ((currentChar = reader.read()) != -1) {
                if (Character.isLetter(currentChar)) {
                    char encodedChar = encodeCharacter((char) currentChar, key.charAt(keyIndex));
                    writer.write(encodedChar);
                    keyIndex = (keyIndex + 1) % key.length();
                } else {
                    writer.write(currentChar);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char encodeCharacter(char character, char keyChar) {
        int base = Character.isUpperCase(character) ? 'A' : 'a';
        int characterIndex = character - base;
        int keyIndex = keyChar - 'A';
        int encodedIndex = (characterIndex + keyIndex) % 26;
        return (char) (base + encodedIndex);
    }

    private static void decodeTextFile(String encodedFilePath, String decodedFilePath, String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(encodedFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(decodedFilePath))) {

            int keyIndex = 0;
            int currentChar;

            while ((currentChar = reader.read()) != -1) {
                if (Character.isLetter(currentChar)) {
                    char decodedChar = decodeCharacter((char) currentChar, key.charAt(keyIndex));
                    writer.write(decodedChar);
                    keyIndex = (keyIndex + 1) % key.length();
                } else {
                    writer.write(currentChar);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char decodeCharacter(char character, char keyChar) {
        int base = Character.isUpperCase(character) ? 'A' : 'a';
        int characterIndex = character - base;
        int keyIndex = keyChar - 'A';
        int decodedIndex = (characterIndex - keyIndex + 26) % 26;
        return (char) (base + decodedIndex);
    }
}
