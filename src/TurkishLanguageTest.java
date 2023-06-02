public class TurkishLanguageTest {

    public static void main(String[] args) {
        TurkishLanguage interpreter = new TurkishLanguage();

        // Test işlemleri için giriş dosyası ve çıkış dosyası
        String inputFilePath = "program.txt";
        String outputFilePath = "output.txt";

        // Test işlemi 1: İki sayıyı topla ve sonucu yazdır
        interpreter.atama("sayi1", 5);
        interpreter.atama("sayi2", 10);
        interpreter.interpret(inputFilePath, outputFilePath);

        // Test işlemi 2: İki sayıyı çarp ve sonucu yazdır
        interpreter.atama("sayi1", 3);
        interpreter.atama("sayi2", 7);
        interpreter.interpret(inputFilePath, outputFilePath);

        // Test işlemi 3: İki sayıyı böle ve sonucu yazdır
        interpreter.atama("sayi1", 20);
        interpreter.atama("sayi2", 4);
        interpreter.interpret(inputFilePath, outputFilePath);

        // Test işlemi 4: Değişkenin değerini doğrudan yazdır
        interpreter.atama("sayi1", 15);
        interpreter.interpret(inputFilePath, outputFilePath);

        // Test işlemi 5: Geçersiz bir komutu çalıştır
        interpreter.atama("sayi1", 8);
        interpreter.interpret(inputFilePath, outputFilePath);
    }
}
