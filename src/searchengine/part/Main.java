package searchengine.part;

public class Main {

	public static void main(String[] args) {

		try {
			// build a lucene index
			System.out.println("rebuildIndexes");
			Indexer indexer = new Indexer();
			indexer.rebuildIndexes();
			System.out.println("rebuildIndexes done");
			// perform search on "Notre Dame museum"
			// and retrieve the top 100 result
//			System.out.println("performSearch");
//			SearchEngine se = new SearchEngine();
//			TopDocs topDocs = se.performSearch("สมมุติ", 100);
//			
//			System.out.println("Results found: " + topDocs.totalHits);
//			ScoreDoc[] hits = topDocs.scoreDocs;
//			for (int i = 0; i < hits.length; i++) {
//				Document doc = se.getDocument(hits[i].doc);
//				
////				System.out.println(doc.get("name") + " " + doc.get("city") + " (" + hits[i].score + ")");
//				String docNameFull = doc.get("doc");
//				String docName = docNameFull.substring(5, docNameFull.length());				
//				System.out.println("Docname: " + docName+ "  Data: " + doc.get("term") + " (" + hits[i].score + ")");
//			}
//			System.out.println("performSearch done");
		} catch (Exception e) {
			System.out.println("Exception caught.\n");
		}

	}
}
