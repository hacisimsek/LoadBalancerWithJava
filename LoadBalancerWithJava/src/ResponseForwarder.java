import java.io.IOException;

class ResponseForwarder {
    public static void forward(java.io.InputStream input, java.io.OutputStream output) throws IOException {
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }
}
