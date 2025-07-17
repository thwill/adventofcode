package de.twisssow.adventofcode.mmxvi;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Day5_part2 {
    static final String INPUT = "ugkcyxxp"; // ← replace with your input

    public static void main(String[] args) throws Exception {
        final char[] password = new char[8];
        final boolean[] filled = new boolean[8];
        final AtomicInteger filledCount = new AtomicInteger(0);

        final MessageDigest md5 = MessageDigest.getInstance("MD5");
        final byte[] inputBytes = INPUT.getBytes(StandardCharsets.UTF_8);
        final int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        System.out.println("Starting with " + numThreads + " threads…");

        CompletionService<Void> cs = new ExecutorCompletionService<>(executor);

        final AtomicInteger counter = new AtomicInteger(0);

        for (int t = 0; t < numThreads; t++) {
            cs.submit(() -> {
                MessageDigest localMd5 = MessageDigest.getInstance("MD5");
                byte[] buffer = new byte[64];
                System.arraycopy(inputBytes, 0, buffer, 0, inputBytes.length);

                while (filledCount.get() < 8) {
                    int idx = counter.getAndIncrement();
                    int len = inputBytes.length + appendNumber(buffer, inputBytes.length, idx);

                    localMd5.reset();
                    localMd5.update(buffer, 0, len);
                    byte[] digest = localMd5.digest();

                    // check if digest starts with 00000 → first 20 bits == 0
                    if (digest[0] == 0 && digest[1] == 0 && (digest[2] & 0xF0) == 0) {
                        int pos = digest[2] & 0x0F;
                        if (pos < 8 && !filled[pos]) {
                            synchronized (filled) {
                                if (!filled[pos]) {
                                    char ch = toHexChar((digest[3] & 0xF0) >> 4);
                                    password[pos] = ch;
                                    filled[pos] = true;
                                    int count = filledCount.incrementAndGet();
                                    System.out.printf("Found position %d → %c (%d/8)%n", pos, ch, count);
                                }
                            }
                        }
                    }
                }
                return null;
            });
        }

        // wait for all work to complete
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);

        System.out.println("Improved password: " + new String(password));
    }

    // appends decimal digits of n into buf at offset, returns number of bytes written
    private static int appendNumber(byte[] buf, int offset, int n) {
        int start = offset;
        int pos = offset;
        do {
            buf[pos++] = (byte) ('0' + (n % 10));
            n /= 10;
        } while (n > 0);

        // reverse the digits
        for (int i = 0; i < (pos - start) / 2; i++) {
            byte tmp = buf[start + i];
            buf[start + i] = buf[pos - 1 - i];
            buf[pos - 1 - i] = tmp;
        }
        return pos - start;
    }

    private static char toHexChar(int nibble) {
        return (char) (nibble < 10 ? '0' + nibble : 'a' + nibble - 10);
    }
}
