package searchengine.part;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.th.ThaiAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import configFile.part.DataFile;

public class Indexer {
	/** Creates a new instance of Indexer */
	private DataFile dataFile = new DataFile();
	
	File folder;
	File[] listFileName;
	public Indexer() {
		folder = new File(dataFile.FOLDER_NAME_IN_DATA);
		listFileName = folder.listFiles();
	}

	private IndexWriter indexWriter = null;
	private BufferedReader reader;

	public IndexWriter getIndexWriter(boolean create) throws IOException {
		if (indexWriter == null) {
			Directory indexDir = FSDirectory.open(new File(dataFile.FOLDER_NAME_OUT_INDEX));
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_2, new ThaiAnalyzer());
			indexWriter = new IndexWriter(indexDir, config);

		}
		return indexWriter;
	}

	public void closeIndexWriter() throws IOException {
		if (indexWriter != null) {
			indexWriter.close();
		}
	}

	public void indexPoem(String data, String docName) throws IOException {
		// System.out.println("Indexing hotel: " + hotel);
		IndexWriter writer = getIndexWriter(false);
		Document doc = new Document();
		doc.add(new TextField("term", data, Field.Store.YES));
		doc.add(new TextField("doc", docName, Field.Store.YES));
		writer.addDocument(doc);
	}

	public void rebuildIndexes() throws IOException {
		//
		// Erase existing index
		//
		getIndexWriter(true);
		//
		// Index all Accommodation entries
		//
		System.out.println(listFileName.length + "ssdads" );
		for (int i = 0; i < listFileName.length; i++) {
			String name = String.valueOf(listFileName[i]);
			String data = readFile(name);
			indexPoem(data, name);
			System.out.println("In loop Lasts");
		}
		// Hotel[] hotels = HotelDatabase.getHotels();
		// for(Hotel hotel : hotels) {
		// indexHotel(hotel);
		// }
		//
		// Don't forget to close the index writer when done
		//
		closeIndexWriter();
	}

	public String readFile(String fileName) throws IOException {

		String data = "";
		reader = new BufferedReader(new FileReader(fileName));
		for (String i = reader.readLine(); i != null; i = reader.readLine()) {
			data = data + " " + i;
		}

		return data;
	}

}
