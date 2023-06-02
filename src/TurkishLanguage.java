import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TurkishLanguage {
    private Map<String, Integer> variables;

    public TurkishLanguage() {
        variables = new HashMap<>();
    }

    public void interpret(String inputFilePath, String outputFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ", 2);  // Split the line into a maximum of two tokens

                if (tokens[0].equalsIgnoreCase("YAZDIR")) {
                    if (tokens.length > 1) {
                        String argument = tokens[1];
                        if (argument.startsWith("\"") && argument.endsWith("\"")) {
                            String message = argument.substring(1, argument.length() - 1);
                            yazdir(writer, message);
                        } else {
                            yazdir(writer, argument);
                        }
                    } else {
                        hata(writer, "YAZDIR komutunun argümanı eksik");
                    }
                } else if (tokens[0].equalsIgnoreCase("ATAMA") || tokens[0].equalsIgnoreCase("ASSIGN")) {
                    if (tokens.length == 2) {
                        String[] assignTokens = tokens[1].split(" ", 2);  // Split the assignTokens into two tokens
                        if (assignTokens.length == 2) {
                            String variableName = assignTokens[0];
                            int value = Integer.parseInt(assignTokens[1]);
                            atama(variableName, value);
                        } else {
                            hata(writer, "ATAMA komutunun argümanları eksik veya fazla");
                        }
                    } else {
                        hata(writer, "ATAMA komutunun argümanları eksik veya fazla");
                    }
                } else if (tokens[0].equalsIgnoreCase("TOPLA") || tokens[0].equalsIgnoreCase("ADD")) {
                    if (tokens.length == 2) {
                        String[] addTokens = tokens[1].split(" ");
                        if (addTokens.length == 3) {
                            String variableName = addTokens[0];
                            Integer operand1 = variables.get(addTokens[1]);
                            Integer operand2 = variables.get(addTokens[2]);
                            if (operand1 != null && operand2 != null) {
                                int result = topla(operand1, operand2);
                                atama(variableName, result);
                            } else {
                                hata(writer, "Değişken bulunamadı: " + addTokens[1] + " veya " + addTokens[2]);
                            }
                        } else {
                            hata(writer, "TOPLA komutunun argümanları eksik veya fazla");
                        }
                    } else {
                        hata(writer, "TOPLA komutunun argümanları eksik veya fazla");
                    }
                } else if (tokens[0].equalsIgnoreCase("CIKAR") || tokens[0].equalsIgnoreCase("SUBTRACT")) {
                    if (tokens.length == 2) {
                        String[] subtractTokens = tokens[1].split(" ");
                        if (subtractTokens.length == 3) {
                            String variableName = subtractTokens[0];
                            Integer operand1 = variables.get(subtractTokens[1]);
                            Integer operand2 = variables.get(subtractTokens[2]);
                            if (operand1 != null && operand2 != null) {
                                int result = cikar(operand1, operand2);
                                atama(variableName, result);
                            } else {
                                hata(writer, "Değişken bulunamadı: " + subtractTokens[1] + " veya " + subtractTokens[2]);
                            }
                        } else {
                            hata(writer, "CIKAR komutunun argümanları eksik veya fazla");
                        }
                    } else {
                        hata(writer, "CIKAR komutunun argümanları eksik veya fazla");
                    }
                } else if (tokens[0].equalsIgnoreCase("CARP") || tokens[0].equalsIgnoreCase("MULTIPLY")) {
                    if (tokens.length == 2) {
                        String[] multiplyTokens = tokens[1].split(" ");
                        if (multiplyTokens.length == 3) {
                            String variableName = multiplyTokens[0];
                            Integer operand1 = variables.get(multiplyTokens[1]);
                            Integer operand2 = variables.get(multiplyTokens[2]);
                            if (operand1 != null && operand2 != null) {
                                int result = carp(operand1, operand2);
                                atama(variableName, result);
                            } else {
                                hata(writer, "Değişken bulunamadı: " + multiplyTokens[1] + " veya " + multiplyTokens[2]);
                            }
                        } else {
                            hata(writer, "CARP komutunun argümanları eksik veya fazla");
                        }
                    } else {
                        hata(writer, "CARP komutunun argümanları eksik veya fazla");
                    }
                } else if (tokens[0].equalsIgnoreCase("BOL") || tokens[0].equalsIgnoreCase("DIVIDE")) {
                    if (tokens.length == 2) {
                        String[] divideTokens = tokens[1].split(" ");
                        if (divideTokens.length == 3) {
                            String variableName = divideTokens[0];
                            Integer operand1 = variables.get(divideTokens[1]);
                            Integer operand2 = variables.get(divideTokens[2]);
                            if (operand1 != null && operand2 != null) {
                                if (operand2 != 0) {
                                    int result = bol(operand1, operand2);
                                    atama(variableName, result);
                                } else {
                                    hata(writer, "Sıfıra bölme hatası: " + divideTokens[2]);
                                }
                            } else {
                                hata(writer, "Değişken bulunamadı: " + divideTokens[1] + " veya " + divideTokens[2]);
                            }
                        } else {
                            hata(writer, "BOL komutunun argümanları eksik veya fazla");
                        }
                    } else {
                        hata(writer, "BOL komutunun argümanları eksik veya fazla");
                    }
                } else {
                    hata(writer, "Geçersiz komut: " + tokens[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atama(String variableName, int value) {
        variables.put(variableName, value);
    }

    public void yazdir(BufferedWriter writer, String message) throws IOException {
        writer.write(message);
        writer.newLine();
    }

    public int topla(int operand1, int operand2) {
        return operand1 + operand2;
    }

    public int cikar(int operand1, int operand2) {
        return operand1 - operand2;
    }

    public int carp(int operand1, int operand2) {
        return operand1 * operand2;
    }

    public int bol(int operand1, int operand2) {
        return operand1 / operand2;
    }

    public void hata(BufferedWriter writer, String errorMessage) throws IOException {
        writer.write("Hata: " + errorMessage);
        writer.newLine();
    }

    public static void main(String[] args) {
        TurkishLanguage interpreter = new TurkishLanguage();
        String inputFilePath = "program.txt";
        String outputFilePath = "output.txt";
        interpreter.interpret(inputFilePath, outputFilePath);
    }
}

