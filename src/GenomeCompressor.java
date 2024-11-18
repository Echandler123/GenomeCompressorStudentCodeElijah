/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {
        String sequence = BinaryStdIn.readString();
        int length = sequence.length();
        // Writes the length as its default 32bit integer value
        BinaryStdOut.write(length);
        // Map of each letter to its integer counterpart
        int[] map = new int[256];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;
        // Turns each letter in the sequence given into its 2bit code
        for(int i = 0; i < length; i++){
            BinaryStdOut.write(map[Integer.parseInt(sequence.substring(i,i+1))], 2);
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        int length = BinaryStdIn.readInt();
        // Map from its integer version to back to the letter itself
        char[] map = new char[4];
        map[0] = 'A';
        map[1] = 'C';
        map[2] = 'G';
        map[3] = 'T';
        // Turns each 2bit code back it their letter counterparts
        for(int i = 0; i < length; i++) {
            BinaryStdOut.write(map[BinaryStdIn.readInt(2)]);
        }
        BinaryStdOut.close();
    }



    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}