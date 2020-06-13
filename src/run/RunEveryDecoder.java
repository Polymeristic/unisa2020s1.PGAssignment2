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

public class RunEveryDecoder {

	public static void runBookCipher(){
		System.out.println("decoding with BookCipher:");

		String msg = "54 27 189 192 78 63 41 25 14 21 52 80 63 63 252 11 1 149 57 52 177 70 16 8 55 8 64 6 48 19 43";
		BookCipher BC = new BookCipher("","1984ch1.txt");
		BC.setMessage(msg,true);

		BC.decodeMessage();
		System.out.println(BC);
	}

	private static void runCaesarCipher(){
		System.out.println("decoding with CaesarCipher:");
		
		String msg = "CKKZ SKNG. PDWP KJA SWO AWOU!";
		char key1 = 'W';
		CaesarCipher CC = new CaesarCipher("",key1);
		CC.setMessage(msg,true);
		CC.decodeMessage();
		System.out.println(CC);

	}

	private static void runChecksumCoder(){
		System.out.println("decoding with ChecksumCoder:");
		String message = "ThisY oneP doesn'tR obscureI theA messageI atR allK";
		ChecksumCoder CSC = new ChecksumCoder("");
		// test encoding with default weights
		CSC.setMessage(message,true);
		CSC.decodeMessage();
		System.out.println(CSC);
		
		System.out.println("*** no check for error detection - yet ***");
	}

	public static void runInterleaver(){
		System.out.println("decoding with Interleaver:");
		String txt = "Gtrr kew.ao Yh oamuva ena ugtneojd um sbt lhmeies  s  .     ";
		Interleaver BI = new Interleaver("",3,4);

		BI.setMessage(txt,true);
		BI.decodeMessage();
		System.out.println(BI);

	}

	public static void runKeywordCipher(){
		System.out.println("decoding with KeywordCipher:");
		String msg = "HUD LJES RJZBSYSI BLS PSHFUYI VMWLSY.";
		String keyword = "Java is cool";
		KeywordCipher KC = new KeywordCipher("", keyword);

		KC.setMessage(msg,true);
		KC.decodeMessage();
		System.out.println(KC);
	}

	public static void runMultiWordCipher(){
		System.out.println("decoding with MultiWordCipher:");
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
		String msg = "carol orangutan nether george roland adder tomato uvula loganberry apple trombone icecream orange nether serpent";
		MultiWordCipher MWC = new MultiWordCipher("",codebook);
		// note we should get a different message each time we encode
		MWC.setMessage(msg,true);
		MWC.decodeMessage();
		System.out.println(MWC);
	}

	private static void runOneWordCipher(){
		System.out.println("decoding with Morse-Code OneWordCipher:");
		String dotdash = "-.--/---/..-/-.-/-./---/.--/..../---/.--/-/---/.-././.-/-../-../---/-/.../.-/-./-../-../.-/.../...././.../";
		OneWordCipher c1 = OneWordCipher.MorseCoder("");
		c1.setMessage(dotdash,true);
		c1.decodeMessage();
		System.out.println(c1);

		System.out.println("decoding with Phonetic OneWordCipher:");
		String alphabravo = "Yankee Oscar Uniform Tango Uniform Romeo November Echo Delta Alfa Foxtrot Oscar X-ray Tango Romeo Oscar Tango India November Tango Oscar Alfa November Foxtrot";
		OneWordCipher c2 = OneWordCipher.PhoneticCoder("");
		c2.setMessage(alphabravo,true);
		c2.decodeMessage();
		System.out.println(c2);

		System.out.println("*** no decode of arbitrary oneword code - yet ***");
	}

	public static void runPermuter(){
		System.out.println("decoding with Permuter:");
		String msg;
		msg = "enWdlo l, eatth aaw  hasdtrge g  aocnrsub m e !l";

		int[] perm = {1,7,0,5,3,6,4,2}; //{1,0,5,3,4,2};
		Permuter PM = new Permuter("",perm);

		PM.setMessage(msg,true);
		PM.decodeMessage();
		System.out.println(PM);
	}

	public static void runPigLatin(){
		System.out.println("decoding with PigLatin:");
		String msg = "Ouyay ememberedray ouryay racticalpay lasscay well.  Oodgay work.";
		PigLatin PL = new PigLatin("");
		PL.setMessage(msg,true);
		PL.decodeMessage();
		System.out.println(PL);

	}

	public static void runTripleRepeat(){
		System.out.println("decoding with TripleRepeat:");
		String msg = "TTThhhiiisss   ooonnneee   iiisss   eeeaaasssyyy...";
		TripleRepeat TR = new TripleRepeat("");
		TR.setMessage(msg,true);
		TR.decodeMessage();
		System.out.println(TR);
	}

	public static void runVigenereCipher(){
		System.out.println("decoding with VigenereCipher:");
		String msg = "TRTTWPTHK LZVZ. SFJ NEC XVRZHT FZZP EC YEXRQP GRRSMCY.";
		String word = "PURPLE";

		VigenereCipher VG = new VigenereCipher("",word);
		VG.setKeepPunct(true);
		VG.setMessage(msg,true);
		
		VG.decodeMessage();
		System.out.println( VG);

	}

	public static void runDictionaryCode() {
		System.out.println("decoding with DictionaryCoder:");
		final String[][] codebook = {
				{"you","he"},
				{"sense","mess"},
				{"nonsense","Java"},
				{"stuff","grief"}
		};

		String message = "He made mess out of java Good grief";

		DictionaryCoder DC = new DictionaryCoder("" , codebook);
		DC.setMessage(message,true);
		
		DC.decodeMessage();
		System.out.println(DC);
	}
	
	///////////////////////////////////////////
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
