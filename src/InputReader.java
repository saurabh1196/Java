import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class InputReader {
    final private int BUFFER_SIZE = 1 << 16;
    private final InputStream stream;
    private final byte[] buffer ;
    private int bufferPointer, bytesRead;

    public InputReader(InputStream stream) {
        this.stream = stream;
        buffer = new byte[1024];
        bufferPointer = bytesRead = 0;
    }

    private int read() {
        if (bytesRead == -1)
            throw new InputMismatchException();
        if (bufferPointer >= bytesRead) {
            bufferPointer =0 ;
            try {
                bytesRead = stream.read(buffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bytesRead <= 0) {
                return -1;
            }
        }
        return buffer[bufferPointer++];
    }

    public int readInt(){
    int c = read();
    while (isSpaceChar(c)){
        c = read();
    }
    boolean neg = (c == '-');
    if(neg){
        c = read();
    }
    int res = 0;
    do{
        res = res *10+(c-'0');
        c = read();
    }
    while (!isSpaceChar(c));
    if(neg)
        return -res;
    return res;
    }

    public long readLong(){
        int c = read();
        while (isSpaceChar(c)){
            c = read();
        }
        boolean neg = (c == '-');
        if(neg){
            c = read();
        }
        long res = 0;
        do{
            res = res *10+(c-'0');
            c = read();
        }
        while (!isSpaceChar(c));
        if(neg)
            return -res;
        return res;
    }

    public double readDouble(){
        int c = read();
        while (isSpaceChar(c)){
            c = read();
        }
        boolean neg = (c == '-');
        if(neg){
            c = read();
        }
        double res = 0;
        double div = 1;
        do{
            res = res *10+(c-'0');
            c = read();
        }
        while (!isSpaceChar(c));
        if(c=='.'){
            c = read();
            while(!isSpaceChar(c)){
                res = res + (c-'0')/(div = div*10);
                c = read();
            }
        }
        if(neg)
            return -res;
        return res;
    }


    public String readString(){
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do{
            res.appendCodePoint(c);
            c = read();
        }
        while (!isSpaceChar(c));
        return res.toString();
    }

    private boolean isSpaceChar(int c){
    return c ==' '|| c== '\n'||c=='\t'|| c== -1 || c=='\r' || c=='.';
    }

}

