import java.util.ArrayList;
import java.util.List;

/**
 * Created by jananiravi on 11/8/15.
 */
public class BreakDocumentIntoChunks {

    public static final int CHUNK_SIZE = 5;
    public static final String DELIMITER = ":";

    public static void main(String[] args) {
        printChunks("abcd:abcdefgh:ab:c:abcdef:a:b:c:");
    }

    public static void printChunks(String doc) {
        List<String> chunkList = chunkify(doc);

        System.out.println("For doc: " + doc + " chunks are:");
        for (String chunk : chunkList) {
            System.out.println(chunk);
            System.out.println();
        }
    }

    public static List<String> chunkify_docs(String doc) {
        List<String> chunkList = new ArrayList<String>();

        String[] paragraphs = doc.split(DELIMITER);

        String currentChunk = "";
        for (String para : paragraphs) {
            // If adding the current paragraph to the current chunk crosses our chunk limit, create a new
            // chunk. First add the current chunk to the list to return.
            if (currentChunk.length() + para.length() > CHUNK_SIZE) {
                if (currentChunk.length() > 0) {
                    chunkList.add(currentChunk);
                    currentChunk = "";
                }
            }
            // If the paragraph itself is larger than our chunk size we handle it
            // separately - add it to a separate chunk by itself.
            if (para.length() > CHUNK_SIZE) {
                // Ensure you add in whatever is present in the current chunk first, otherwise
                // the chunk list will not have the right order of paragraphs.
                if (currentChunk.length() > 0) {
                    chunkList.add(currentChunk);
                    currentChunk = "";
                }
                chunkList.add(para + DELIMITER);
                continue;
            }

            // Keep adding the paragraphs to the current chunk.
            currentChunk += para + DELIMITER;
        }

        if (currentChunk.length() > 0) {
            chunkList.add(currentChunk);
        }

        return chunkList;
    }

    public static List<String> chunkify(String doc) {
        List<String> chunkList = new ArrayList<String>();

        String[] paragraphs = doc.split(DELIMITER);

        String currentChunk = "";
        for (String para : paragraphs) {
            if (currentChunk.length() + para.length() > CHUNK_SIZE) {
                if (currentChunk.length() > 0) {
                    chunkList.add(currentChunk);
                    currentChunk = "";
                }
            }

            if (para.length() > CHUNK_SIZE) {
                if (currentChunk.length() > 0) {
                    chunkList.add(currentChunk);
                    currentChunk = "";
                }
                chunkList.add(para + DELIMITER);
                continue;
            }

            currentChunk += para + DELIMITER;
        }

        if (currentChunk.length() > 0) {
            chunkList.add(currentChunk);
        }

        return chunkList;
    }

}
