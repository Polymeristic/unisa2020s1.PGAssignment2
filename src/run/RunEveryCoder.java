package run;

import main.BookCipher;
import main.CaesarCipher;
import main.ChecksumCoder;
import main.DictionaryCoder;
import main.Interleaver;
import main.KeywordCipher;
import main.MultiWordCipher;
import main.OneWordCipher;
import main.Permuter;
import main.PigLatin;
import main.TripleRepeat;
import main.VigenereCipher;

/**
 * This class simply runs every coder forward (encoding) and 
 * backwards (decoding) on some text.  This is NOT a substitute for
 * full testing of the classes - you need to write your own tests.
 * 
 * Rather, the purpose of this program is to make sure that your own 
 * methods adhere to the specified interface.  Check that your constructors
 * have the right parameters in the right order.  Check that your other 
 * methods have correctly-spelled names, correct parameters and return 
 * values.
 * 
 * Be aware that the runs in this program do NOT cover every possible 
 * use-case of each class.
 * 
 * If you only want to run one or two of the text coders then comment out 
 * the ones which don't interest you in the main() method.
 * 
 * @author sjs
 *
 */
public class RunEveryCoder {

	public static void runBookCipher(){
		// be aware that the coded messages should be different
		// each time you run this.

		String msg = "He loved Big Brother.";
		BookCipher BC = new BookCipher(msg,"1984ch1.txt");
		BC.encodeMessage();
		System.out.println(BC);
		BC.decodeMessage();
		System.out.println(BC);

	}

	private static void runCaesarCipher(){
		String msg = "Hail, Caesar!";
		
		// choose one or other of these:
		//char key1 = 'M';
		//CaesarCipher CC = new CaesarCipher(msg,key1);
		String key1 = "Minotaur";
		CaesarCipher CC = new CaesarCipher(msg,key1);
		
		CC.setKeepPunct(true); // choose true or false as you desire.
		
		CC.encodeMessage();
		System.out.println(CC);
		
		// clear the key, and try to decode
		boolean OK;
		CC.clearKey();
		OK = CC.decodeMessage();
		System.out.println("Decode returned: "+ OK);
		System.out.println("present encoded status: " + CC.isCoded());
		
		// set the key again, try to decode again
		CC.setKey(key1);
		OK = CC.decodeMessage();
		System.out.println("Decode returned: "+ OK);
		System.out.println("present encoded status: " + CC.isCoded());
		System.out.println(CC);
	}

	private static void runChecksumCoder(){

		ChecksumCoder CSC = new ChecksumCoder(""); 
		
		// comment this in/out if you desire
		int[] weigh = {2,3,7};  // these numbers should be coprime
		CSC.setWeights(weigh);
		
		String message = "Peter Piper picked a peck of pickled peppers";
		CSC.setMessage(message, false);
		CSC.encodeMessage();
		System.out.println(CSC);

		String data = CSC.getMessage();
		// corrupt a character and put data back
		data = data.substring(0,9) + "X" + data.substring(10);
		CSC.setMessage(data,true);
		System.out.println("causing an error: " + CSC);
		//System.out.println(CSC);
		CSC.decodeMessage();
		System.out.println(CSC);
	}

	public static void runInterleaver(){
		String txt = "Welcome to Jamaica. I hope you have an enjoyable stay.";
		Interleaver BI = new Interleaver(txt,3,4);

		System.out.println(BI);
		BI.encodeMessage();
		System.out.println(BI);
		BI.decodeMessage();
		System.out.println(BI);
	}

	public static void runKeywordCipher(){
		String msg = "The quick brown fox jumps over the lazy dog";
		String keyword = "Java is cool";
		KeywordCipher KC = new KeywordCipher(msg, keyword);

		//KC.setKeepPunct(false);  // make this true or false at your leisure

		KC.encodeMessage();
		System.out.println(KC);
		KC.decodeMessage();
		System.out.println(KC);
	}

	public static void runMultiWordCipher(){
		String[][] codebook = {
				{"apple","aardvark","adder"},
				{"banana","baby"},
				{"cheese","carol"},
				{"dessert","death"},
				{"eggplant","every","easy"},
				{"fish","flake"},
				{"gerbil","george"},
				{"haircut","harry"},
				{"icecream"},
				{"jaffa","jelly"},
				{"kite","king"},
				{"loganberry"},
				{"mouse","muscle","maiden"},
				{"nether"},
				{"orange","orangutan"},
				{"police","purple"},
				{"queen"},
				{"rabbit","roland"},
				{"snake","serpent","slither"},
				{"tomato","trombone"},
				{"uvula"},
				{"violent","viola"},
				{"wet","whelk"},
				{"xray"},
				{"yellow"},
				{"zebra"} };
		// it's not a very good code, is it?
		
		String msg = "Lovely day";
		MultiWordCipher MWC = new MultiWordCipher(msg,codebook);
		// note we should get a different message each time we encode
		MWC.encodeMessage();
		System.out.println(MWC);
		MWC.decodeMessage();
		System.out.println(MWC);
	}

	private static void runOneWordCipher(){
		OneWordCipher c1 = OneWordCipher.MorseCoder("This is all dots and dashes.");
		System.out.println(c1);
		c1.encodeMessage();
		System.out.println(c1);
		c1.decodeMessage();
		System.out.println(c1+"\n----");

		OneWordCipher c2 = OneWordCipher.PhoneticCoder("Hello Sailor!");
		System.out.println(c2);
		c2.encodeMessage();
		System.out.println(c2);
		c2.decodeMessage();
		System.out.println(c2+"\n----");

		String myCode = "Ay Bee See Dee Ee Eff Gee Aitch Eye Jay Kay El Em En Oh Pee Queue Arr Ess Tee You Vee Doubleyou Ecks Why Zed";
		OneWordCipher c3 = new OneWordCipher("I hope this works",myCode,'/');
		System.out.println(c3);
		c3.encodeMessage();
		System.out.println(c3);
		c3.decodeMessage();
		System.out.println(c3);
	}

	public static void runPermuter(){
		String msg;
		msg = "This will become an awful mess";

		int[] perm = {1,7,0,5,3,6,4,2}; //{1,0,5,3,4,2};
		Permuter PM = new Permuter(msg,perm);

		System.out.println(PM);
		PM.encodeMessage();
		System.out.println(PM);
		PM.decodeMessage();
		System.out.println(PM);
	}

	public static void runPigLatin(){
		String msg = "Mortal actions never deceive the gods.";
		PigLatin PL = new PigLatin(msg);
		PL.encodeMessage();
		System.out.println(PL);
		PL.decodeMessage();
		System.out.println(PL);

		msg = "Plain English and Igpay Atinlay";
		PL.setMessage(msg,true);
		PL.decodeMessage();
		System.out.println(PL);
	}

	public static void runTripleRepeat(){
		String msg = "Watch me grow in length!";
		TripleRepeat TR = new TripleRepeat(msg);
		TR.encodeMessage();
		System.out.println(TR);
		TR.decodeMessage();
		System.out.println(TR);

		String errs = "TTThhhixisss   iiisDw   aaa   TTTedessxtt!";
		TR.setMessage(errs,true);
		TR.decodeMessage();
		System.out.println(TR);
	}

	public static void runVigenereCipher(){
		String msg = "I've got a lovely bunch of coconuts.";
		String word = "PURPLE";

		VigenereCipher VG = new VigenereCipher(msg,word);
		VG.setKeepPunct(true);
		
		//VG.setKeepPunct(false);  // uncomment if you wish
		//VG.clearKey();           // this too

		System.out.println("original :    " + VG);
		boolean OK = VG.encodeMessage();
		System.out.println("post-encode : " + VG + "  (" + OK + ")");
		//VG.clearKey();  // uncomment if you like
		OK = VG.decodeMessage();
		System.out.println("post-decode : " + VG + "  (" + OK + ")");
		System.out.println("final status: " + VG.isCoded());
	}

	public static void runDictionaryCode() {
		final String[][] codebook = {
				{"agent","espion"},
				{"partner","partenaire"},
				{"window","fenetre"},
				{"door","porte"},
				{"station","gare"},
				{"shop","magasin"},
				{"hat","chapeau"},
				{"coat","manteau"},
				{"shoes","chaussures"},
				{"flower","fleur"},
				{"red","rouge"},
				{"green","vert"},
				{"white","blanc"},
				{"black","noir"}
		};

		String message = "Meet the agent in the white hat by the door.";

		DictionaryCoder DC = new DictionaryCoder(message , codebook);

		System.out.println(DC);
		DC.encodeMessage();
		System.out.println(DC);
		DC.decodeMessage();
		System.out.println(DC);
	}
	
	///////////////////////////////////////////
	
	/**
	 * This method simply calls each of the run methods.
	 * Comment out the ones you don't want (or which you haven't coded yet)
	 * and uncomment them when you want them.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		runBookCipher();
		System.out.println("\n========\n");

		runCaesarCipher();
		System.out.println("\n========\n");

		runChecksumCoder();
		System.out.println("\n========\n");

		runInterleaver();
		System.out.println("\n========\n");

		runKeywordCipher();
		System.out.println("\n========\n");

		runMultiWordCipher();
		System.out.println("\n========\n");

		runOneWordCipher();
		System.out.println("\n========\n");

		runPermuter();
		System.out.println("\n========\n");

		runPigLatin();
		System.out.println("\n========\n");
		
		runTripleRepeat();
		System.out.println("\n========\n");
		
		runVigenereCipher();
		System.out.println("\n========\n");
		
		runDictionaryCode();
		System.out.println("\n========\n");
	}

}
