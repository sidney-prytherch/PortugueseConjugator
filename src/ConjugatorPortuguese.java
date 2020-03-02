
public class ConjugatorPortuguese {

	private static final String DEF = "{d}";

	public static void main(String[] args) {
		VerbForm[] allForms = new VerbForm[] { VerbForm.PRES_IND,
				VerbForm.PRET_IND, VerbForm.IMP_IND, VerbForm.SIMP_PLUP_IND,
				VerbForm.FUT_IND, VerbForm.COND_IND,

				VerbForm.PRES_PERF, VerbForm.PLUP, VerbForm.FUT_PERF,
				VerbForm.COND_PERF,

				VerbForm.PRES_PROG, VerbForm.PRET_PROG, VerbForm.IMP_PROG,
				VerbForm.SIMP_PLUP_PROG, VerbForm.FUT_PROG, VerbForm.COND_PROG,
				VerbForm.PRES_PERF_PROG, VerbForm.PLUP_PROG,
				VerbForm.FUT_PERF_PROG, VerbForm.COND_PERF_PROG,

				VerbForm.PRES_SUBJ, VerbForm.PRES_PERF_SUBJ, VerbForm.IMP_SUBJ,
				VerbForm.PLUP_SUBJ, VerbForm.FUT_SUBJ, VerbForm.FUT_PERF_SUBJ,

				VerbForm.IMP_AFF, VerbForm.IMP_NEG, VerbForm.PERS_INF };
		String[][] conjugatedVerb = conjugate("abrir", allForms, false);
		for (int i = 0; i < conjugatedVerb.length; i++) {
			System.out.println(allForms[i].toString());
			for (int j = 0; j < conjugatedVerb[i].length; j++) {
				System.out.print(conjugatedVerb[i][j] + ", ");
			}
			System.out.println("\n");
		}
	}

	public static class VerbData {
		private VerbClass verbClass;
		private String stem, eAndIStem, aAndOStem, presSubjStem, impSubjStem,
				futSubjStem, futAndCondStem, participle, gerund;
		private boolean isArEndings;

		public VerbData(VerbClass verbClass, boolean isArEndings, String stem,
				String eAndIStem, String aAndOStem, String presSubjStem,
				String impSubjStem, String futSubjStem, String futAndCondStem,
				String participle, String gerund) {
			this.verbClass = verbClass;
			this.stem = stem;
			this.aAndOStem = aAndOStem;
			this.eAndIStem = eAndIStem;
			this.presSubjStem = presSubjStem;
			this.impSubjStem = impSubjStem;
			this.futSubjStem = futSubjStem;
			this.participle = participle;
			this.gerund = gerund;
			this.isArEndings = isArEndings;
			this.futAndCondStem = futAndCondStem;
		}

		public VerbClass getVerbClass() {
			return verbClass;
		}

		public boolean getIsArEndings() {
			return this.isArEndings;
		}

		public String getStem() {
			return stem;
		}

		public String getEAndIStem() {
			return eAndIStem;
		}

		public String getAAndOStem() {
			return aAndOStem;
		}

		public String getPresSubjStem() {
			return presSubjStem;
		}

		public String getImpSubjStem() {
			return impSubjStem;
		}

		public String getFutSubjStem() {
			return futSubjStem;
		}

		public String getParticiple() {
			return participle;
		}

		public String getGerund() {
			return gerund;
		}

		public String getFutAndCondStem() {
			return futAndCondStem;
		}
	}

	public enum VerbForm {
		PRES_IND, PRET_IND, IMP_IND, SIMP_PLUP_IND, FUT_IND, COND_IND,

		PRES_PERF, PLUP, FUT_PERF, COND_PERF,

		PRES_PROG, PRET_PROG, IMP_PROG, SIMP_PLUP_PROG, FUT_PROG, COND_PROG,
		PRES_PERF_PROG, PLUP_PROG, FUT_PERF_PROG, COND_PERF_PROG,

		PRES_SUBJ, PRES_PERF_SUBJ, IMP_SUBJ, PLUP_SUBJ, FUT_SUBJ, FUT_PERF_SUBJ,

		IMP_AFF, IMP_NEG,

		PERS_INF
	}

	private static String[][] subjects = new String[][] { { "Eu" }, { "Tu" },
			{ "Você", "Ele", "Ela" }, { "Nós" }, { "Vós" },
			{ "Vocês", "Eles", "Elas" } };

	public enum VerbType {
		IRREGULAR, AR, ER, IR
	}

	public enum VerbClass {
		REG_AR, REG_ER, REG_IR, AIR, OLIR, ODIR_ATIR, ABRIR, TER, VIR, FAZER,
		IAR, POR, DIZER, CABER, SEGUIR, CRER, COBRIR, DAR, ESTAR, GEAR, HAVER,
		IR, LER, QUERER, NEVAR, OUVIR, PERDER, PODER, VER, PROVER, RIR, SABER,
		SER, TOSSIR, TRAZER, VALER, ENGOLIR, FUGIR, GREDIR, EDIR, DIVERTIR,
		SERVIR, ENTIR, SAUDAR, REUNIR, ELIR, REMIR, EAR, OIBIR, PREVENIR, ERIR,
		OER, VESTIR, DORMIR, TUIR, BUIR, STRUIR, ERZIR, PÔR, OIAR
	}

	/*
	 * public static String getVerbFormString(VerbForm verbForm, Resources
	 * resources) { switch (verbForm) { case PRES_IND: return
	 * resources.getString(R.string.present); case PRET_IND: return
	 * resources.getString(R.string.preterite); case IMP_IND: return
	 * resources.getString(R.string.imperfect); case SIMP_PLUP_IND: return
	 * resources.getString(R.string.simple_pluperfect); case FUT_IND: return
	 * resources.getString(R.string.future); case COND_IND: return
	 * resources.getString(R.string.conditional); case PRES_PERF: return
	 * resources.getString(R.string.present_perfect); case PLUP: return
	 * resources.getString(R.string.pluperfect); case FUT_PERF: return
	 * resources.getString(R.string.future_perfect); case COND_PERF: return
	 * resources.getString(R.string.conditional_perfect); case PRES_PROG: return
	 * resources.getString(R.string.present_progressive); case PRET_PROG: return
	 * resources.getString(R.string.preterite_progressive); case IMP_PROG:
	 * return resources.getString(R.string.imperfect_progressive); case
	 * SIMP_PLUP_PROG: return
	 * resources.getString(R.string.simple_pluperfect_progressive); case
	 * FUT_PROG: return resources.getString(R.string.future_progressive); case
	 * COND_PROG: return resources.getString(R.string.conditional_progressive);
	 * case PRES_PERF_PROG: return
	 * resources.getString(R.string.present_perfect_progressive); case
	 * PLUP_PROG: return resources.getString(R.string.pluperfect_progressive);
	 * case FUT_PERF_PROG: return
	 * resources.getString(R.string.future_perfect_progressive); case
	 * COND_PERF_PROG: return
	 * resources.getString(R.string.conditional_perfect_progressive); case
	 * PRES_SUBJ: return resources.getString(R.string.present_subjunctive); case
	 * PRES_PERF_SUBJ: return
	 * resources.getString(R.string.present_perfect_subjunctive); case IMP_SUBJ:
	 * return resources.getString(R.string.imperfect_subjunctive); case
	 * PLUP_SUBJ: return resources.getString(R.string.pluperfect_subjunctive);
	 * case FUT_SUBJ: return resources.getString(R.string.future_subjunctive);
	 * case FUT_PERF_SUBJ: return
	 * resources.getString(R.string.future_perfect_subjunctive); } return null;
	 * }
	 */

	public static String getSubject(int index) {
		return subjects[index][(int) (Math.random() * subjects[index].length)];
	}

	private static String getEIStem(String infinitive, int verbLength,
			String stem) {
		if (verbLength > 2) {
			switch (infinitive.substring(verbLength - 3, verbLength)) {
			case "car":
				return infinitive.substring(0, verbLength - 3) + "qu";
			case "çar":
				return infinitive.substring(0, verbLength - 3) + "c";
			case "gar":
				return infinitive.substring(0, verbLength - 3) + "gu";
			}
		}
		return stem;
	}

	private static String getAOStem(String infinitive, int verbLength,
			String stem) {
		if (verbLength > 2) {
			switch (infinitive.substring(verbLength - 3, verbLength)) {
			case "cer":
			case "cir":
				return infinitive.substring(0, verbLength - 3) + "ç";
			case "ger":
			case "gir":
				return infinitive.substring(0, verbLength - 3) + "j";
			}
			if (verbLength > 3) {
				switch (infinitive.substring(verbLength - 4, verbLength)) {
				case "guir":
				case "guer":
					return infinitive.substring(0, verbLength - 3);
				}

			}
		}
		return stem;
	}

	private static boolean matches(String infinitive, int verbLength,
			String match) {
		int matchLength = match.length();
		if (verbLength >= matchLength
				&& infinitive.substring(verbLength - matchLength, verbLength)
						.equals(match)) {
			return true;
		}
		return false;
	}

	private static VerbData getVerbClassAndStems(String infinitive,
			int verbLength) {
		VerbClass verbClass = null;
		String stem = null;
		String eAndIStem = null;
		String aAndOStem = null;
		String presSubjStem = null;
		String futSubjStem = null;
		String futAndCondStem = null;
		String impSubjStem = null;
		String participle = null;
		String gerund = null;
		boolean isArEndings = false;
		boolean isArEndingsSet = false;
		String ending = infinitive.substring(verbLength - 2, verbLength);
//		if (infinitive.matches("astrair|cair|decair|descair|embair|extrair|protrair|recair|sair|sobressair|subtrair|trair|contrair")) {
		if (matches(infinitive, verbLength, "air")) {
			verbClass = VerbClass.AIR;
			stem = infinitive.substring(0, verbLength - 3);
			gerund = stem + "aindo";
			participle = stem + "aído";
			presSubjStem = stem + "ai";
			impSubjStem = stem + "aí";
			futSubjStem = stem + "a";
		} else if (infinitive.matches("abolir|demolir")) {
			verbClass = VerbClass.OLIR;
			String stem2 = infinitive.substring(0, verbLength - 2);
			stem = infinitive.substring(0, verbLength - 4);
			presSubjStem = DEF + stem + "ul";
			impSubjStem = stem2;
		} else if (infinitive.matches("explodir|latir")) {
			verbClass = VerbClass.ODIR_ATIR;
			stem = infinitive.substring(0, verbLength - 2);
			presSubjStem = DEF + stem;
			impSubjStem = stem;
//		} else if (infinitive.matches("abrir|reabrir")) {
		} else if (matches(infinitive, verbLength, "abrir")) {
			verbClass = VerbClass.ABRIR;
			stem = infinitive.substring(0, verbLength - 5);
			gerund = stem + "abrindo";
			participle = stem + "aberto";
			presSubjStem = stem + "abr";
			impSubjStem = stem + "abr";
			stem = stem + "abr";
		} else if (infinitive.matches(
				"ter|abster|conter|suster|ater|deter|entreter|manter|obter|reter")) {
			verbClass = VerbClass.TER;
			stem = infinitive.substring(0, verbLength - 3);
			presSubjStem = stem + "tenh";
			impSubjStem = stem + "tiv";
			futSubjStem = stem + "tiver";
		} else if (infinitive.matches(
				"vir|devir|advir|avir|convir|desavir|intervir|provir|sobrevir")) {
			verbClass = VerbClass.VIR;
			stem = infinitive.substring(0, verbLength - 3);
			gerund = participle = stem + "vindo";
			presSubjStem = stem + "venh";
			impSubjStem = stem + "vi";
			futSubjStem = stem + "vier";
//		} else if (infinitive.matches("afazer|contrafazer|desfazer|fazer|liquefazer|perfazer|rarefazer|refazer|satisfazer|putrefazer|tumefazer")) {
		} else if (matches(infinitive, verbLength, "fazer")) {
			verbClass = VerbClass.FAZER;
			stem = infinitive.substring(0, verbLength - 5);
			gerund = stem + "fazendo";
			participle = stem + "feito";
			presSubjStem = stem + "faç";
			impSubjStem = stem + "fiz";
			futSubjStem = stem + "fizer";
			futAndCondStem = stem + "far";
		} else if (infinitive.matches(
				"ansiar|incendiar|mediar|odiar|remediar|intermediar")) {
			verbClass = VerbClass.IAR;
			stem = infinitive.substring(0, verbLength - 3);
			presSubjStem = stem + "ei";
			impSubjStem = stem + "i";
			// } else if
			// (infinitive.matches("antepor|compor|dispor|opor|propor|supor|apor|contrapor|decompor|depor|descompor|expor|interpor|justapor|pospor|pressupor|repor|sobrepor|transpor"))
			// {
		} else if (matches(infinitive, verbLength, "por")) {
			verbClass = VerbClass.POR;
			stem = infinitive.substring(0, verbLength - 3);
			// } else if
			// (infinitive.matches("bendizer|condizer|contradizer|desdizer|dizer|maldizer|predizer"))
			// {
		} else if (matches(infinitive, verbLength, "dizer")) {
			verbClass = VerbClass.DIZER;
			stem = infinitive.substring(0, verbLength - 5);
		} else if (infinitive.matches("caber")) {
			verbClass = VerbClass.CABER;
			stem = "cab";
			// } else if
			// (infinitive.matches("conseguir|perseguir|prosseguir|seguir|desconseguir|desseguir"))
			// {
		} else if (matches(infinitive, verbLength, "seguir")) {
			verbClass = VerbClass.SEGUIR;
			stem = infinitive.substring(0, verbLength - 6);
			// } else if (infinitive.matches("crer|descrer")) {
		} else if (matches(infinitive, verbLength, "crer")) {
			verbClass = VerbClass.CRER;
			stem = infinitive.substring(0, verbLength - 4);
			// } else if
			// (infinitive.matches("cobrir|descobrir|recobrir|redescobrir")) {
		} else if (matches(infinitive, verbLength, "encobrir")) {
			verbClass = VerbClass.COBRIR;
			stem = infinitive.substring(0, verbLength - 6);
		} else if (infinitive.matches("dar|desdar")) {
			verbClass = VerbClass.DAR;
			stem = infinitive.substring(0, verbLength - 3);
		} else if (infinitive.matches("estar")) {
			verbClass = VerbClass.ESTAR;
		} else if (infinitive.matches("gear")) {
			verbClass = VerbClass.GEAR;
		} else if (infinitive.matches("haver")) {
			verbClass = VerbClass.HAVER;
		} else if (infinitive.matches("ir")) {
			verbClass = VerbClass.IR;
		} else if (infinitive.matches("ler|reler|tresler")) {
			verbClass = VerbClass.LER;
			stem = infinitive.substring(0, verbLength - 3);
//		} else if (infinitive.matches("querer|malquerer")) {
		} else if (matches(infinitive, verbLength, "querer")) {
			verbClass = VerbClass.QUERER;
			stem = infinitive.substring(0, verbLength - 6);
		} else if (infinitive.matches("nevar")) {
			verbClass = VerbClass.NEVAR;
		} else if (infinitive.matches("ouvir")) {
			verbClass = VerbClass.OUVIR;
		} else if (infinitive.matches("perder")) {
			verbClass = VerbClass.PERDER;
		} else if (infinitive.matches("poder")) {
			verbClass = VerbClass.PODER;
		} else if (infinitive.matches("pôr")) {
			verbClass = VerbClass.PÔR;
		} else if (infinitive.matches("antever|entrever|prever|rever|ver")) {
			verbClass = VerbClass.VER;
			stem = infinitive.substring(0, verbLength - 3);
		} else if (infinitive.matches("prover")) {
			verbClass = VerbClass.PROVER;
		} else if (infinitive.matches("rir|sorrir")) {
			verbClass = VerbClass.RIR;
			stem = infinitive.substring(0, verbLength - 3);
		} else if (infinitive.matches("saber")) {
			verbClass = VerbClass.SABER;
		} else if (infinitive.matches("ser")) {
			verbClass = VerbClass.SER;
		} else if (infinitive.matches("tossir")) {
			verbClass = VerbClass.TOSSIR;
		} else if (infinitive.matches("trazer")) {
			verbClass = VerbClass.TRAZER;
		} else if (matches(infinitive, verbLength, "valer")) {
			verbClass = VerbClass.VALER;
			stem = infinitive.substring(0, verbLength - 5);
		} else if (infinitive.matches("engolir")) {
			verbClass = VerbClass.ENGOLIR;
			// weird one (ortho + stem?)
		} else if (infinitive.matches("fugir")) {
			verbClass = VerbClass.FUGIR;
			// radical changing
		} else if (matches(infinitive, verbLength, "gredir")) {
			verbClass = VerbClass.GREDIR;
			stem = infinitive.substring(0, verbLength - 6);
		} else if (matches(infinitive, verbLength, "edir")) {
			verbClass = VerbClass.EDIR;
			stem = infinitive.substring(0, verbLength - 4);
		} else if (infinitive.matches("divertir")) {
			verbClass = VerbClass.DIVERTIR;
		} else if (infinitive.matches("servir")) {
			verbClass = VerbClass.SERVIR;
		} else if (matches(infinitive, verbLength, "entir")) {
			verbClass = VerbClass.ENTIR;
			stem = infinitive.substring(0, verbLength - 5);
			// weird one
		} else if (infinitive.matches("saudar")) {
			verbClass = VerbClass.SAUDAR;
			// weird one
		} else if (infinitive.matches("reunir")) {
			verbClass = VerbClass.REUNIR;
		} else if (matches(infinitive, verbLength, "elir")) {
			verbClass = VerbClass.ELIR;
			stem = infinitive.substring(0, verbLength - 4);
		} else if (infinitive.matches("remir")) {
			verbClass = VerbClass.REMIR;
		} else if (matches(infinitive, verbLength, "ear")) {
			verbClass = VerbClass.EAR;
			stem = infinitive.substring(0, verbLength - 3);
		} else if (matches(infinitive, verbLength, "oibir")) {
			verbClass = VerbClass.OIBIR;
			stem = infinitive.substring(0, verbLength - 5);
		} else if (matches(infinitive, verbLength, "prevenir")) {
			verbClass = VerbClass.PREVENIR;
			stem = infinitive.substring(0, verbLength - 8);
		} else if (matches(infinitive, verbLength, "erir")) {
			verbClass = VerbClass.ERIR;
			stem = infinitive.substring(0, verbLength - 4);
		} else if (infinitive.matches("apoiar|boiar")) {
			verbClass = VerbClass.OIAR;
			stem = infinitive.substring(0, verbLength - 3);
		} else if (matches(infinitive, verbLength, "oer")) {
			verbClass = VerbClass.OER;
			stem = infinitive.substring(0, verbLength - 3);
		} else if (matches(infinitive, verbLength, "vestir")) {
			verbClass = VerbClass.VESTIR;
			stem = infinitive.substring(0, verbLength - 6);
		} else if (infinitive.matches("dormir")) {
			verbClass = VerbClass.DORMIR;
		} else if (matches(infinitive, verbLength, "tuir")) {
			verbClass = VerbClass.TUIR;
			stem = infinitive.substring(0, verbLength - 4);
		} else if (matches(infinitive, verbLength, "buir")) {
			verbClass = VerbClass.BUIR;
			stem = infinitive.substring(0, verbLength - 4);
		} else if (matches(infinitive, verbLength, "struir")) {
			verbClass = VerbClass.STRUIR;
			stem = infinitive.substring(0, verbLength - 6);
		} else if (matches(infinitive, verbLength, "erzir")) {
			verbClass = VerbClass.ERZIR;
			stem = infinitive.substring(0, verbLength - 5);
		} else {
			verbClass = ending.equals("AR") ? VerbClass.REG_AR
					: ending.equals("ER") ? VerbClass.REG_ER : VerbClass.REG_IR;
			eAndIStem = verbClass == VerbClass.REG_AR
					? getEIStem(infinitive, verbLength, stem)
					: stem;
			aAndOStem = (verbClass == VerbClass.REG_ER
					|| verbClass == VerbClass.REG_IR)
							? getAOStem(infinitive, verbLength, stem)
							: stem;
		}
		if (participle == null) {
			if (ending.equals("ar")) {
				String substr = infinitive.substring(0,
						infinitive.length() - 1);
				participle = substr + "do";
				gerund = substr + "ndo";
				if (!isArEndingsSet) {
					isArEndings = true;
				}
			} else {
				participle = infinitive.substring(0, infinitive.length() - 2)
						+ "ido";
				gerund = infinitive.substring(0, infinitive.length() - 1)
						+ "ndo";
				if (!isArEndingsSet) {
					isArEndings = false;
				}
			}
		}
		return new VerbData(verbClass, isArEndings, stem,
				eAndIStem == null ? stem : eAndIStem,
				aAndOStem == null ? stem : aAndOStem, presSubjStem, impSubjStem,
				futSubjStem == null ? infinitive : futSubjStem,
				futAndCondStem == null ? infinitive : futAndCondStem,
				participle, gerund);
	}

	public static String[][] conjugate(String infinitive, VerbForm[] verbForms,
			boolean portugal) {
		if (infinitive == null) {
			return null;
		}
		infinitive = infinitive.toLowerCase();
		int verbLength = infinitive.length();
		String[][] verbsToReturn = new String[verbForms.length][6];
		if (!infinitive.contains(" ") && !infinitive.contains("-")
				&& !infinitive.contains("+") && verbLength > 1
				&& verbForms != null && verbForms.length > 0
				&& infinitive.charAt(verbLength - 1) == 'r') {
			VerbData verbData = getVerbClassAndStems(infinitive, verbLength);
			VerbClass verbClass = verbData.getVerbClass();
			String stem = verbData.getStem();
			String aAndOStem = verbData.getAAndOStem();
			String eAndIStem = verbData.getEAndIStem();
			String presSubjStem = verbData.getPresSubjStem();
			String impSubjStem = verbData.getImpSubjStem();
			String futSubjStem = verbData.getFutSubjStem();
			String participle = verbData.getParticiple();
			String gerund = verbData.getGerund();
			String futAndCondStem = verbData.getFutAndCondStem();
			boolean isArEndings = verbData.getIsArEndings();
//			String subjRoot1 = "";
//			String subjRoot2 = "";
			/*
			 * if (verbClass.equals("ir")) { switch (infinitive) { case
			 * "abolir": specialStem = "abul"; break; case "conseguir":
			 * specialStem = "consig"; break; case "divertir": specialStem =
			 * "divirt"; break; case "mentir": specialStem = "mint"; break; case
			 * "desmentir": specialStem = "desmint"; break; case "repetir":
			 * specialStem = "repit"; break; case "seguir": specialStem = "sig";
			 * break; case "desseguir": specialStem = "dessig"; break; case
			 * "perseguir": specialStem = "persig"; break; case "prosseguir":
			 * specialStem = "prossig"; break; case "sentir": specialStem =
			 * "sint"; break; case "assentir": specialStem = "assint"; break;
			 * case "consentir": specialStem = "consint"; break; case
			 * "dissentir": specialStem = "dissint"; break; case "pressentir":
			 * specialStem = "pressint"; break; case "ressentir": specialStem =
			 * "ressint"; break; case "servir": specialStem = "sirv"; break;
			 * case "vestir": specialStem = "vist"; break; case "investir":
			 * specialStem = "invist"; break; case "revestir": specialStem =
			 * "revist"; break; case "cobrir": specialStem = "cubr"; break; case
			 * "descobrir": specialStem = "descubr"; break; case "encobrir":
			 * specialStem = "encubr"; break; case "recobrir": specialStem =
			 * "recubr"; break; case "redescobrir": specialStem = "redescubr";
			 * break; case "dormir": specialStem = "durm"; break; case "erguer":
			 * specialStem = "erg"; } }
			 * 
			 * String subjRoot1; String subjRoot2; verbsToReturn = new
			 * String[verbForms.length][6]; switch (infinitive) { case "dizer":
			 * subjRoot1 = "dig"; subjRoot2 = "disseram"; break; case "crer":
			 * subjRoot1 = "crei"; subjRoot2 = stem; break; case "ler":
			 * subjRoot1 = "lei"; subjRoot2 = stem; break; case "medir":
			 * subjRoot1 = "meç"; subjRoot2 = stem; break; case "ouvir":
			 * subjRoot1 = "ouç"; subjRoot2 = stem; break; case "pedir":
			 * subjRoot1 = "peç"; subjRoot2 = stem; break; case "perder":
			 * subjRoot1 = "perc"; subjRoot2 = stem; break; case "rir":
			 * subjRoot1 = "ri"; subjRoot2 = stem; break; case "valer":
			 * subjRoot1 = "valh"; subjRoot2 = stem; break; default: subjRoot1 =
			 * stem; subjRoot2 = stem; } checkForSpecialVerbs(infinitive,
			 * verbForms, verbsToReturn);
			 */

			for (int i = 0; i < verbForms.length; i++) {
				switch (verbForms[i]) {
				case PRES_IND:
					verbsToReturn[i] = conjugatePresInd(infinitive, verbClass,
							stem, aAndOStem, eAndIStem);
					break;
				case PRET_IND:
					verbsToReturn[i] = conjugatePretInd(infinitive, verbClass,
							stem, aAndOStem, eAndIStem);
					break;
				case IMP_IND:
					verbsToReturn[i] = conjugateImpInd(infinitive, verbClass,
							stem, aAndOStem, eAndIStem);
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = conjugateSimpPlupInd(infinitive,
							verbClass, stem, aAndOStem, eAndIStem);
					break;
				case FUT_IND:
					verbsToReturn[i] = new String[] { futAndCondStem + "ei",
							futAndCondStem + "ás", futAndCondStem + "á",
							futAndCondStem + "emos", futAndCondStem + "eis",
							futAndCondStem + "ão" };

					break;
				case COND_IND:
					verbsToReturn[i] = new String[] { futAndCondStem + "ia",
							futAndCondStem + "ias", futAndCondStem + "ia",
							futAndCondStem + "íamos", futAndCondStem + "íeis",
							futAndCondStem + "iam" };

					break;
				case PRES_PERF:
					if (participle != null) {
						verbsToReturn[i] = new String[] { "tenho " + participle,
								"tens " + participle, "tem " + participle,
								"temos " + participle, "tendes " + participle,
								"têm " + participle };
					}
					break;
				case PLUP:
					if (participle != null) {
						verbsToReturn[i] = new String[] { "tinha " + participle,
								"tinhas " + participle, "tinha " + participle,
								"tínhamos " + participle,
								"tínheis " + participle,
								"tinham " + participle };
					}
					break;
				case FUT_PERF:
					if (participle != null) {
						verbsToReturn[i] = new String[] { "terei " + participle,
								"terás " + participle, "terá " + participle,
								"teremos " + participle, "tereis " + participle,
								"terão " + participle };
					}
					break;
				case COND_PERF:
					if (participle != null) {
						verbsToReturn[i] = new String[] { "teria " + participle,
								"terias " + participle, "teria " + participle,
								"teríamos " + participle,
								"teríeis " + participle,
								"teriam " + participle };
					}
					break;
				case PRES_PROG:
					if (portugal || gerund == null) {
						verbsToReturn[i] = new String[] {
								"estou a " + infinitive,
								"estás a " + infinitive, "está a " + infinitive,
								"estamos a " + infinitive,
								"estais a " + infinitive,
								"estão a " + infinitive };
					} else {
						verbsToReturn[i] = new String[] { "estou " + gerund,
								"estás " + gerund, "está " + gerund,
								"estamos " + gerund, "estais " + gerund,
								"estão " + gerund };
					}
					break;
				case IMP_PROG:
					if (portugal || gerund == null) {
						verbsToReturn[i] = new String[] {
								"estava a " + infinitive,
								"estavas a " + infinitive,
								"estava a " + infinitive,
								"estávamos a " + infinitive,
								"estáveis a " + infinitive,
								"estavam a " + infinitive };
					} else {
						verbsToReturn[i] = new String[] { "estava " + gerund,
								"estavas " + gerund, "estava " + gerund,
								"estávamos " + gerund, "estáveis " + gerund,
								"estavam " + gerund };
					}
					break;
				case PRET_PROG:
					if (portugal || gerund == null) {
						verbsToReturn[i] = new String[] {
								"estive a " + infinitive,
								"estiveste a " + infinitive,
								"esteve a " + infinitive,
								"estivemos a " + infinitive,
								"estivestes a " + infinitive,
								"estiveram a " + infinitive };
					} else {
						verbsToReturn[i] = new String[] { "estive " + gerund,
								"estiveste " + gerund, "esteve " + gerund,
								"estivemos " + gerund, "estivestes " + gerund,
								"estiveram " + gerund };
					}
					break;
				case SIMP_PLUP_PROG:
					if (portugal || gerund == null) {
						verbsToReturn[i] = new String[] {
								"estivera a " + infinitive,
								"estiveras a " + infinitive,
								"estivera a " + infinitive,
								"estivéramos a " + infinitive,
								"estivéreis a " + infinitive,
								"estiveram a " + infinitive };
					} else {
						verbsToReturn[i] = new String[] { "estivera " + gerund,
								"estiveras " + gerund, "estivera " + gerund,
								"estivéramos " + gerund, "estivéreis " + gerund,
								"estiveram " + gerund };
					}
					break;
				case FUT_PROG:
					if (portugal || gerund == null) {
						verbsToReturn[i] = new String[] {
								"estarei a " + infinitive,
								"estarás a " + infinitive,
								"estará a " + infinitive,
								"estaremos a " + infinitive,
								"estareis a " + infinitive,
								"estarão a " + infinitive };
					} else {
						verbsToReturn[i] = new String[] { "estarei " + gerund,
								"estarás " + gerund, "estará " + gerund,
								"estaremos " + gerund, "estareis " + gerund,
								"estarão " + gerund };
					}
					break;
				case COND_PROG:
					if (portugal || gerund == null) {
						verbsToReturn[i] = new String[] {
								"estaria a " + infinitive,
								"estarias a " + infinitive,
								"estaria a " + infinitive,
								"estaríamos a " + infinitive,
								"estaríeis a " + infinitive,
								"estariam a " + infinitive };
					} else {
						verbsToReturn[i] = new String[] { "estaria " + gerund,
								"estarias " + gerund, "estaria " + gerund,
								"estaríamos " + gerund, "estaríeis " + gerund,
								"estariam " + gerund };
					}
					break;
				case PRES_PERF_PROG:
					if (portugal || gerund == null) {
						verbsToReturn[i] = new String[] {
								"tenho estado a " + infinitive,
								"tens estado a " + infinitive,
								"tem estado a " + infinitive,
								"temos estado a " + infinitive,
								"tendes estado a " + infinitive,
								"têm estado a " + infinitive };
					} else {
						verbsToReturn[i] = new String[] {
								"tenho estado " + gerund,
								"tens estado " + gerund, "tem estado " + gerund,
								"temos estado " + gerund,
								"tendes estado " + gerund,
								"têm estado " + gerund };
					}
					break;
				case PLUP_PROG:
					if (portugal || gerund == null) {
						verbsToReturn[i] = new String[] {
								"tinha estado a " + infinitive,
								"tinhas estado a " + infinitive,
								"tinha estado a " + infinitive,
								"tínhamos estado a " + infinitive,
								"tínheis estado a " + infinitive,
								"tinham estado a " + infinitive };
					} else {
						verbsToReturn[i] = new String[] {
								"tinha estado " + gerund,
								"tinhas estado " + gerund,
								"tinha estado " + gerund,
								"tínhamos estado " + gerund,
								"tínheis estado " + gerund,
								"tinham estado " + gerund };
					}
					break;
				case FUT_PERF_PROG:
					if (portugal || gerund == null) {
						verbsToReturn[i] = new String[] {
								"terei estado a " + infinitive,
								"terás estado a " + infinitive,
								"terá estado a " + infinitive,
								"teremos estado a " + infinitive,
								"tereis estado a " + infinitive,
								"terão estado a " + infinitive };
					} else {
						verbsToReturn[i] = new String[] {
								"terei estado " + gerund,
								"terás estado " + gerund,
								"terá estado " + gerund,
								"teremos estado " + gerund,
								"tereis estado " + gerund,
								"terão estado " + gerund };
					}
					break;
				case COND_PERF_PROG:
					if (portugal || gerund == null) {
						verbsToReturn[i] = new String[] {
								"teria estado a " + infinitive,
								"terias estado a " + infinitive,
								"teria estado a " + infinitive,
								"teríamos estado a " + infinitive,
								"teríeis estado a " + infinitive,
								"teriam estado a " + infinitive };
					} else {
						verbsToReturn[i] = new String[] {
								"teria estado " + gerund,
								"terias estado " + gerund,
								"teria estado " + gerund,
								"teríamos estado " + gerund,
								"teríeis estado " + gerund,
								"teriam estado " + gerund };
					}
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] {
							presSubjStem + (isArEndings ? "e" : "a"),
							presSubjStem + (isArEndings ? "es" : "as"),
							presSubjStem + (isArEndings ? "e" : "a"),
							presSubjStem + (isArEndings ? "emos" : "amos"),
							presSubjStem + (isArEndings ? "eis" : "ais"),
							presSubjStem + (isArEndings ? "em" : "am") };
					break;
				case PRES_PERF_SUBJ:
					if (participle != null) {
						verbsToReturn[i] = new String[] { "tenha " + participle,
								"tenhas " + participle, "tenha " + participle,
								"tenhamos " + participle,
								"tenhais " + participle,
								"tenham " + participle };
					}
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = conjugateImpSubj(infinitive, verbClass,
							impSubjStem);
					break;
				case PLUP_SUBJ:
					if (participle != null) {
						verbsToReturn[i] = new String[] {
								"tivesse " + participle,
								"tivesses " + participle,
								"tivesse " + participle,
								"tivéssemos " + participle,
								"tivésseis " + participle,
								"tivessem " + participle };
					}
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = conjugateFutSubj(infinitive, verbClass,
							futSubjStem);
					break;
				case FUT_PERF_SUBJ:
					if (participle != null) {
						verbsToReturn[i] = new String[] { "tiver " + participle,
								"tiveres " + participle, "tiver " + participle,
								"tivermos " + participle,
								"tiverdes " + participle,
								"tiveram " + participle };
					}
					break;
				case IMP_AFF:
					verbsToReturn[i] = conjugateImpAff(infinitive, verbClass,
							stem);
					break;
				case IMP_NEG:
					verbsToReturn[i] = new String[] {
							(verbClass == VerbClass.OLIR
									|| verbClass == VerbClass.ODIR_ATIR)
											? presSubjStem + "a"
											: null,
							presSubjStem + (isArEndings ? "es" : "as"),
							presSubjStem + (isArEndings ? "e" : "a"),
							presSubjStem + (isArEndings ? "emos" : "amos"),
							presSubjStem + (isArEndings ? "eis" : "ais"),
							presSubjStem + (isArEndings ? "em" : "am") };
					break;
				case PERS_INF:
					verbsToReturn[i] = conjugatePersInf(infinitive, verbClass,
							stem);
				}
			}
		}
		return verbsToReturn;

	}

	private static String[] conjugatePresInd(String infinitive,
			VerbClass verbClass, String stem, String aAndOStem,
			String eAndIStem) {
		switch (verbClass) {
		case REG_AR:
			break;
		case REG_ER:
			break;
		case REG_IR:
			break;
		case AIR:
			return new String[] { stem + "aio", stem + "ais", stem + "ai",
					stem + "aímos", stem + "aís", stem + "aem" };
		case OLIR:
			return new String[] { DEF + stem + "ulo", DEF + stem + "oles",
					DEF + stem + "ole", stem + "olimos", stem + "olis",
					DEF + stem + "olem" };
		case ODIR_ATIR:
			return new String[] { DEF + stem + "o", stem + "es", stem + "e",
					stem + "imos", stem + "is", stem + "em" };
		case ABRIR:
			return new String[] { stem + "o", stem + "es", stem + "e",
					stem + "imos", stem + "is", stem + "em" };
		case TER:
			if (stem.isEmpty()) {
				return new String[] { "tenho", "tens", "tem", "temos", "tendes",
						"têm" };
			}
			return new String[] { stem + "tenho", stem + "téns", stem + "tém",
					stem + "temos", stem + "tendes", stem + "têm" };
		case VIR:
			if (stem.isEmpty()) {
				return new String[] { "venho", "vens", "vem", "vimos", "vindes",
						"vêm" };
			}
			return new String[] { stem + "venho", stem + "véns", stem + "vém",
					stem + "vimos", stem + "vindes", stem + "vêm" };
		case FAZER:
			return new String[] { stem + "faço", stem + "fazes", stem + "faz",
					stem + "fazemos", stem + "fazeis", stem + "fazem" };
		case IAR:
			return new String[] { stem + "eio", stem + "eias", stem + "eia",
					stem + "iamos", stem + "iais", stem + "eiam" };
		case POR:
			break;
		case DIZER:
			break;
		case CABER:
			break;
		case SEGUIR:
			break;
		case CRER:
			break;
		case COBRIR:
			break;
		case DAR:
			break;
		case ESTAR:
			break;
		case GEAR:
			break;
		case HAVER:
			break;
		case IR:
			break;
		case LER:
			break;
		case QUERER:
			break;
		case NEVAR:
			break;
		case OUVIR:
			break;
		case PERDER:
			break;
		case PODER:
			break;
		case VER:
			break;
		case PROVER:
			break;
		case RIR:
			break;
		case SABER:
			break;
		case SER:
			break;
		case TOSSIR:
			break;
		case TRAZER:
			break;
		case VALER:
			break;
		case ENGOLIR:
			break;
		case FUGIR:
			break;
		case GREDIR:
			break;
		case EDIR:
			break;
		case DIVERTIR:
			break;
		case SERVIR:
			break;
		case ENTIR:
			break;
		case SAUDAR:
			break;
		case REUNIR:
			break;
		case ELIR:
			break;
		case REMIR:
			break;
		case EAR:
			break;
		case OIBIR:
			break;
		case PREVENIR:
			break;
		case ERIR:
			break;
		case OIAR:
			break;
		case OER:
			break;
		case VESTIR:
			break;
		case DORMIR:
			break;
		case TUIR:
			break;
		case BUIR:
			break;
		case STRUIR:
			break;
		case ERZIR:
			break;
		case PÔR:
			break;
		}
		return null;
	}

	private static String[] conjugatePretInd(String infinitive,
			VerbClass verbClass, String stem, String aAndOStem,
			String eAndIStem) {
		switch (verbClass) {
		case REG_AR:
			break;
		case REG_ER:
			break;
		case REG_IR:
			break;
		case AIR:
			return new String[] { stem + "aí", stem + "aíste", stem + "aiu",
					stem + "aímos", stem + "aístes", stem + "aíram" };
		case OLIR:
			return new String[] { stem + "oli", stem + "oliste", stem + "oliu",
					stem + "olimos", stem + "olistes", stem + "oliram" };
		case ODIR_ATIR:
		case ABRIR:
			return new String[] { stem + "i", stem + "iste", stem + "iu",
					stem + "imos", stem + "istes", stem + "iram" };
		case TER:
			return new String[] { stem + "tive", stem + "tiveste",
					stem + "teve", stem + "tivemos", stem + "tivestes",
					stem + "tiveram" };
		case VIR:
			return new String[] { stem + "vim", stem + "vieste", stem + "veio",
					stem + "viemos", stem + "viestes", stem + "vieram" };
		case FAZER:
			return new String[] { stem + "fiz", stem + "fizeste", stem + "fez",
					stem + "fizemos", stem + "fizestes", stem + "fizeram" };
		case IAR:
			return new String[] { stem + "iei", stem + "iaste", stem + "iou",
					stem + "iamos" /* alternative with accent */,
					stem + "iastes", stem + "iaram" };
		case POR:
			break;
		case DIZER:
			break;
		case CABER:
			break;
		case SEGUIR:
			break;
		case CRER:
			break;
		case COBRIR:
			break;
		case DAR:
			break;
		case ESTAR:
			break;
		case GEAR:
			break;
		case HAVER:
			break;
		case IR:
			break;
		case LER:
			break;
		case QUERER:
			break;
		case NEVAR:
			break;
		case OUVIR:
			break;
		case PERDER:
			break;
		case PODER:
			break;
		case VER:
			break;
		case PROVER:
			break;
		case RIR:
			break;
		case SABER:
			break;
		case SER:
			break;
		case TOSSIR:
			break;
		case TRAZER:
			break;
		case VALER:
			break;
		case ENGOLIR:
			break;
		case FUGIR:
			break;
		case GREDIR:
			break;
		case EDIR:
			break;
		case DIVERTIR:
			break;
		case SERVIR:
			break;
		case ENTIR:
			break;
		case SAUDAR:
			break;
		case REUNIR:
			break;
		case ELIR:
			break;
		case REMIR:
			break;
		case EAR:
			break;
		case OIBIR:
			break;
		case PREVENIR:
			break;
		case ERIR:
			break;
		case OIAR:
			break;
		case OER:
			break;
		case VESTIR:
			break;
		case DORMIR:
			break;
		case TUIR:
			break;
		case BUIR:
			break;
		case STRUIR:
			break;
		case ERZIR:
			break;
		case PÔR:
			break;
		}
		return null;
	}

	private static String[] conjugateImpInd(String infinitive,
			VerbClass verbClass, String stem, String aAndOStem,
			String eAndIStem) {

		switch (verbClass) {
		case REG_AR:
			break;
		case REG_ER:
			break;
		case REG_IR:
			break;
		case AIR:
			return new String[] { stem + "aía", stem + "aías", stem + "aía",
					stem + "aíamos", stem + "aíeis", stem + "aíam" };
		case OLIR:
			return new String[] { stem + "olia", stem + "olias", stem + "olia",
					stem + "olíamos", stem + "olíeis", stem + "oliam" };
		case ODIR_ATIR:
		case ABRIR:
			return new String[] { stem + "ia", stem + "ias", stem + "ia",
					stem + "íamos", stem + "íeis", stem + "iam" };
		case TER:
			return new String[] { stem + "tinha", stem + "tinhas",
					stem + "tinha", stem + "tínhamos", stem + "tínheis",
					stem + "tinham" };
		case VIR:
			return new String[] { stem + "vinha", stem + "vinhas",
					stem + "vinha", stem + "vínhamos", stem + "vínheis",
					stem + "vinham" };
		case FAZER:
			return new String[] { stem + "fazia", stem + "fazias",
					stem + "fazia", stem + "fazíamos", stem + "fazíeis",
					stem + "faziam" };
		case IAR:
			return new String[] { stem + "iava", stem + "iavas", stem + "iava",
					stem + "iávamos", stem + "iáveis", stem + "iavam" };
		case POR:
			break;
		case DIZER:
			break;
		case CABER:
			break;
		case SEGUIR:
			break;
		case CRER:
			break;
		case COBRIR:
			break;
		case DAR:
			break;
		case ESTAR:
			break;
		case GEAR:
			break;
		case HAVER:
			break;
		case IR:
			break;
		case LER:
			break;
		case QUERER:
			break;
		case NEVAR:
			break;
		case OUVIR:
			break;
		case PERDER:
			break;
		case PODER:
			break;
		case VER:
			break;
		case PROVER:
			break;
		case RIR:
			break;
		case SABER:
			break;
		case SER:
			break;
		case TOSSIR:
			break;
		case TRAZER:
			break;
		case VALER:
			break;
		case ENGOLIR:
			break;
		case FUGIR:
			break;
		case GREDIR:
			break;
		case EDIR:
			break;
		case DIVERTIR:
			break;
		case SERVIR:
			break;
		case ENTIR:
			break;
		case SAUDAR:
			break;
		case REUNIR:
			break;
		case ELIR:
			break;
		case REMIR:
			break;
		case EAR:
			break;
		case OIBIR:
			break;
		case PREVENIR:
			break;
		case ERIR:
			break;
		case OIAR:
			break;
		case OER:
			break;
		case VESTIR:
			break;
		case DORMIR:
			break;
		case TUIR:
			break;
		case BUIR:
			break;
		case STRUIR:
			break;
		case ERZIR:
			break;
		case PÔR:
			break;
		}
		return null;
	}

	private static String[] conjugateSimpPlupInd(String infinitive,
			VerbClass verbClass, String stem, String aAndOStem,
			String eAndIStem) {
		switch (verbClass) {
		case REG_AR:
			break;
		case REG_ER:
			break;
		case REG_IR:
			break;
		case AIR: // {s} + aír
			return new String[] { stem + "aíra", stem + "aíras", stem + "aíra",
					stem + "aíramos", stem + "aíreis", stem + "aíram" };
		case OLIR:
			return new String[] { stem + "olira", stem + "oliras",
					stem + "olira", stem + "olíramos", stem + "olíreis",
					stem + "oliram" };
		case ODIR_ATIR:
		case ABRIR:
			return new String[] { stem + "ira", stem + "iras", stem + "ira",
					stem + "íramos", stem + "íreis", stem + "iram" };
		case TER: // {s} + tiv + (er|ér)
			return new String[] { stem + "tivera", stem + "tiveras",
					stem + "tivera", stem + "tivéramos", stem + "tivéreis",
					stem + "tiveram" };
		case VIR: // {s} + vi + (er|ér)
			return new String[] { stem + "viera", stem + "vieras",
					stem + "viera", stem + "viéramos", stem + "viéreis",
					stem + "vieram" };
		case FAZER:
			return new String[] { stem + "fizera", stem + "fizeras",
					stem + "fizera", stem + "fizéramos", stem + "fizéreis",
					stem + "fizeram" };
		case IAR:
			return new String[] { stem + "iara", stem + "iaras", stem + "iara",
					stem + "iáramos", stem + "iáreis", stem + "iaram" };
		case POR:
			break;
		case DIZER:
			break;
		case CABER:
			break;
		case SEGUIR:
			break;
		case CRER:
			break;
		case COBRIR:
			break;
		case DAR:
			break;
		case ESTAR:
			break;
		case GEAR:
			break;
		case HAVER:
			break;
		case IR:
			break;
		case LER:
			break;
		case QUERER:
			break;
		case NEVAR:
			break;
		case OUVIR:
			break;
		case PERDER:
			break;
		case PODER:
			break;
		case VER:
			break;
		case PROVER:
			break;
		case RIR:
			break;
		case SABER:
			break;
		case SER:
			break;
		case TOSSIR:
			break;
		case TRAZER:
			break;
		case VALER:
			break;
		case ENGOLIR:
			break;
		case FUGIR:
			break;
		case GREDIR:
			break;
		case EDIR:
			break;
		case DIVERTIR:
			break;
		case SERVIR:
			break;
		case ENTIR:
			break;
		case SAUDAR:
			break;
		case REUNIR:
			break;
		case ELIR:
			break;
		case REMIR:
			break;
		case EAR:
			break;
		case OIBIR:
			break;
		case PREVENIR:
			break;
		case ERIR:
			break;
		case OIAR:
			break;
		case OER:
			break;
		case VESTIR:
			break;
		case DORMIR:
			break;
		case TUIR:
			break;
		case BUIR:
			break;
		case STRUIR:
			break;
		case ERZIR:
			break;
		case PÔR:
			break;
		}
		return null;
	}

//	private static String[] conjugatePresSubj(String infinitive,
//			VerbClass verbClass, String[] stems) {
//		switch (verbClass) {
//		case REG_AR:
//			break;
//		case REG_ER:
//			break;
//		case REG_IR:
//			break;
//		case AIR:
//			break;
//		case O_IR:
//			break;
//		case ABRIR:
//			break;
//		case TER: // s+tenh
//			return new String[] { stems[0] + "tenha", stems[0] + "tenhas",
//					stems[0] + "tenha", stems[0] + "tenhamos",
//					stems[0] + "tenhais", stems[0] + "tenham" };
//		case VIR: // s+venh
//			return new String[] { stems[0] + "venha", stems[0] + "venhas",
//					stems[0] + "venha", stems[0] + "venhamos",
//					stems[0] + "venhais", stems[0] + "venham" };
//		case FAZER:
//			break;
//		case IAR:
//			break;
//		case POR:
//			break;
//		case DIZER:
//			break;
//		case CABER:
//			break;
//		case SEGUIR:
//			break;
//		case CRER:
//			break;
//		case COBRIR:
//			break;
//		case DAR:
//			break;
//		case ESTAR:
//			break;
//		case GEAR:
//			break;
//		case HAVER:
//			break;
//		case IR:
//			break;
//		case LER:
//			break;
//		case QUERER:
//			break;
//		case NEVAR:
//			break;
//		case OUVIR:
//			break;
//		case PERDER:
//			break;
//		case PODER:
//			break;
//		case VER:
//			break;
//		case PROVER:
//			break;
//		case RIR:
//			break;
//		case SABER:
//			break;
//		case SER:
//			break;
//		case TOSSIR:
//			break;
//		case TRAZER:
//			break;
//		case VALER:
//			break;
//		case ENGOLIR:
//			break;
//		case FUGIR:
//			break;
//		case GREDIR:
//			break;
//		case EDIR:
//			break;
//		case DIVERTIR:
//			break;
//		case SERVIR:
//			break;
//		case ENTIR:
//			break;
//		case SAUDAR:
//			break;
//		case REUNIR:
//			break;
//		case ELIR:
//			break;
//		case REMIR:
//			break;
//		case EAR:
//			break;
//		case OIBIR:
//			break;
//		case PREVENIR:
//			break;
//		case ERIR:
//			break;
//		case OIAR:
//			break;
//		case OER:
//			break;
//		case VESTIR:
//			break;
//		case DORMIR:
//			break;
//		case TUIR:
//			break;
//		case BUIR:
//			break;
//		case STRUIR:
//			break;
//		case ERZIR:
//			break;
//		case PÔR:
//			break;
//		}
//		return null;
//	}

	private static String[] conjugateImpSubj(String infinitive,
			VerbClass verbClass, String impSubjStem) {
		switch (verbClass) {
		case REG_AR:
			break;
		case REG_ER:
			break;
		case REG_IR:
			break;
		case AIR:
			return new String[] { impSubjStem + "sse", impSubjStem + "sses",
					impSubjStem + "sse", impSubjStem + "ssemos",
					impSubjStem + "sseis", impSubjStem + "ssem" };
		case OLIR:
		case ODIR_ATIR:
		case ABRIR:
			return new String[] { impSubjStem + "isse", impSubjStem + "isses",
					impSubjStem + "isse", impSubjStem + "íssemos",
					impSubjStem + "ísseis", impSubjStem + "issem" };
		case TER: // s+tiv
			return new String[] { impSubjStem + "esse", impSubjStem + "esses",
					impSubjStem + "esse", impSubjStem + "éssemos",
					impSubjStem + "ésseis", impSubjStem + "essem" };
		case VIR: // s+vi
		case FAZER:
			return new String[] { impSubjStem + "esse", impSubjStem + "esses",
					impSubjStem + "esse", impSubjStem + "éssemos",
					impSubjStem + "ésseis", impSubjStem + "essem" };
		case IAR:
			return new String[] { impSubjStem + "asse", impSubjStem + "asses",
					impSubjStem + "asse", impSubjStem + "ássemos",
					impSubjStem + "ásseis", impSubjStem + "assem" };
		case POR:
			break;
		case DIZER:
			break;
		case CABER:
			break;
		case SEGUIR:
			break;
		case CRER:
			break;
		case COBRIR:
			break;
		case DAR:
			break;
		case ESTAR:
			break;
		case GEAR:
			break;
		case HAVER:
			break;
		case IR:
			break;
		case LER:
			break;
		case QUERER:
			break;
		case NEVAR:
			break;
		case OUVIR:
			break;
		case PERDER:
			break;
		case PODER:
			break;
		case VER:
			break;
		case PROVER:
			break;
		case RIR:
			break;
		case SABER:
			break;
		case SER:
			break;
		case TOSSIR:
			break;
		case TRAZER:
			break;
		case VALER:
			break;
		case ENGOLIR:
			break;
		case FUGIR:
			break;
		case GREDIR:
			break;
		case EDIR:
			break;
		case DIVERTIR:
			break;
		case SERVIR:
			break;
		case ENTIR:
			break;
		case SAUDAR:
			break;
		case REUNIR:
			break;
		case ELIR:
			break;
		case REMIR:
			break;
		case EAR:
			break;
		case OIBIR:
			break;
		case PREVENIR:
			break;
		case ERIR:
			break;
		case OIAR:
			break;
		case OER:
			break;
		case VESTIR:
			break;
		case DORMIR:
			break;
		case TUIR:
			break;
		case BUIR:
			break;
		case STRUIR:
			break;
		case ERZIR:
			break;
		case PÔR:
			break;
		}
		return null;
	}

	private static String[] conjugateFutSubj(String infinitive,
			VerbClass verbClass, String futSubjStem) {
		switch (verbClass) {
		case REG_AR:
			break;
		case REG_ER:
			break;
		case REG_IR:
			break;
		case AIR:
			return new String[] { futSubjStem + "ir", futSubjStem + "íres",
					futSubjStem + "ir", futSubjStem + "irmos",
					futSubjStem + "irdes", futSubjStem + "írem" };
		case OLIR: // infinitive
		case ODIR_ATIR:
		case VIR: // s+vi
		case TER: // s+tiv
		case ABRIR:
		case FAZER:
		case IAR:
			return new String[] { futSubjStem, futSubjStem + "es", futSubjStem,
					futSubjStem + "mos", futSubjStem + "des",
					futSubjStem + "em" };
		case POR:
			break;
		case DIZER:
			break;
		case CABER:
			break;
		case SEGUIR:
			break;
		case CRER:
			break;
		case COBRIR:
			break;
		case DAR:
			break;
		case ESTAR:
			break;
		case GEAR:
			break;
		case HAVER:
			break;
		case IR:
			break;
		case LER:
			break;
		case QUERER:
			break;
		case NEVAR:
			break;
		case OUVIR:
			break;
		case PERDER:
			break;
		case PODER:
			break;
		case VER:
			break;
		case PROVER:
			break;
		case RIR:
			break;
		case SABER:
			break;
		case SER:
			break;
		case TOSSIR:
			break;
		case TRAZER:
			break;
		case VALER:
			break;
		case ENGOLIR:
			break;
		case FUGIR:
			break;
		case GREDIR:
			break;
		case EDIR:
			break;
		case DIVERTIR:
			break;
		case SERVIR:
			break;
		case ENTIR:
			break;
		case SAUDAR:
			break;
		case REUNIR:
			break;
		case ELIR:
			break;
		case REMIR:
			break;
		case EAR:
			break;
		case OIBIR:
			break;
		case PREVENIR:
			break;
		case ERIR:
			break;
		case OIAR:
			break;
		case OER:
			break;
		case VESTIR:
			break;
		case DORMIR:
			break;
		case TUIR:
			break;
		case BUIR:
			break;
		case STRUIR:
			break;
		case ERZIR:
			break;
		case PÔR:
			break;
		}
		return null;
	}

	private static String[] conjugateImpAff(String infinitive,
			VerbClass verbClass, String stem) {
		switch (verbClass) {
		case REG_AR:
			break;
		case REG_ER:
			break;
		case REG_IR:
			break;
		case AIR:
			return new String[] { null, stem + "ai", stem + "aia",
					stem + "aiamos", stem + "aí", stem + "aiam" };
		case OLIR: /*
					 * some had alternate forms it seemed that weren't defective
					 */
			return new String[] { DEF + stem + "ola", DEF + stem + "olas",
					DEF + stem + "ola", DEF + stem + "olamos",
					DEF + stem + "olais", DEF + stem + "olam" };
		case ODIR_ATIR: /*
						 * some had alternate forms it seemed that weren't
						 * defective
						 */
			return new String[] { DEF + stem + "a", DEF + stem + "as",
					DEF + stem + "a", DEF + stem + "amos", DEF + stem + "ais",
					DEF + stem + "am" };
		case ABRIR:
			return new String[] { null, stem + "e", stem + "a", stem + "amos",
					stem + "i", stem + "am" };
		case TER: // s+tiv
			return new String[] { null, stem + (stem.isEmpty() ? "tem" : "tém"),
					stem + "tenha", stem + "tenhamos", stem + "tende",
					stem + "tenham" };
		case VIR: // s+vi
			return new String[] { null, stem + (stem.isEmpty() ? "vem" : "vém"),
					stem + "venha", stem + "venhamos", stem + "vinde",
					stem + "venham" };
		case FAZER:
			return new String[] { null, stem + "faz" /* or faze */,
					stem + "faça", stem + "façamos", stem + "fazei",
					stem + "façam" };
		case IAR:
			return new String[] { null, stem + "eia", stem + "eie",
					stem + "iemos", stem + "iai", stem + "eiem" };
		case POR:
			break;
		case DIZER:
			break;
		case CABER:
			break;
		case SEGUIR:
			break;
		case CRER:
			break;
		case COBRIR:
			break;
		case DAR:
			break;
		case ESTAR:
			break;
		case GEAR:
			break;
		case HAVER:
			break;
		case IR:
			break;
		case LER:
			break;
		case QUERER:
			break;
		case NEVAR:
			break;
		case OUVIR:
			break;
		case PERDER:
			break;
		case PODER:
			break;
		case VER:
			break;
		case PROVER:
			break;
		case RIR:
			break;
		case SABER:
			break;
		case SER:
			break;
		case TOSSIR:
			break;
		case TRAZER:
			break;
		case VALER:
			break;
		case ENGOLIR:
			break;
		case FUGIR:
			break;
		case GREDIR:
			break;
		case EDIR:
			break;
		case DIVERTIR:
			break;
		case SERVIR:
			break;
		case ENTIR:
			break;
		case SAUDAR:
			break;
		case REUNIR:
			break;
		case ELIR:
			break;
		case REMIR:
			break;
		case EAR:
			break;
		case OIBIR:
			break;
		case PREVENIR:
			break;
		case ERIR:
			break;
		case OIAR:
			break;
		case OER:
			break;
		case VESTIR:
			break;
		case DORMIR:
			break;
		case TUIR:
			break;
		case BUIR:
			break;
		case STRUIR:
			break;
		case ERZIR:
			break;
		case PÔR:
			break;
		}
		return null;
	}

	private static String[] conjugatePersInf(String infinitive,
			VerbClass verbClass, String stem) {
		switch (verbClass) {
		case REG_AR:
			break;
		case REG_ER:
			break;
		case REG_IR:
			break;
		case AIR:
			return new String[] { infinitive, stem + "aíres", infinitive,
					infinitive + "mos", infinitive + "des", stem + "aírem" };
		case OLIR:
		case ODIR_ATIR:
		case ABRIR:
		case TER:
		case FAZER:
		case VIR:
		case IAR:
			return new String[] { infinitive, infinitive + "es", infinitive,
					infinitive + "mos", infinitive + "des", infinitive + "em" };
		case POR:
			break;
		case DIZER:
			break;
		case CABER:
			break;
		case SEGUIR:
			break;
		case CRER:
			break;
		case COBRIR:
			break;
		case DAR:
			break;
		case ESTAR:
			break;
		case GEAR:
			break;
		case HAVER:
			break;
		case IR:
			break;
		case LER:
			break;
		case QUERER:
			break;
		case NEVAR:
			break;
		case OUVIR:
			break;
		case PERDER:
			break;
		case PODER:
			break;
		case VER:
			break;
		case PROVER:
			break;
		case RIR:
			break;
		case SABER:
			break;
		case SER:
			break;
		case TOSSIR:
			break;
		case TRAZER:
			break;
		case VALER:
			break;
		case ENGOLIR:
			break;
		case FUGIR:
			break;
		case GREDIR:
			break;
		case EDIR:
			break;
		case DIVERTIR:
			break;
		case SERVIR:
			break;
		case ENTIR:
			break;
		case SAUDAR:
			break;
		case REUNIR:
			break;
		case ELIR:
			break;
		case REMIR:
			break;
		case EAR:
			break;
		case OIBIR:
			break;
		case PREVENIR:
			break;
		case ERIR:
			break;
		case OIAR:
			break;
		case OER:
			break;
		case VESTIR:
			break;
		case DORMIR:
			break;
		case TUIR:
			break;
		case BUIR:
			break;
		case STRUIR:
			break;
		case ERZIR:
			break;
		case PÔR:
			break;
		}
		return null;
	}

	public static VerbType getVerbType(String infinitive) {
		if (infinitive == null) {
			return null;
		}
		infinitive = infinitive.toLowerCase();
		String[] irregularVerbs = { "abster", "ater", "conter", "dar", "deter",
				"dizer", "entreter", "estar", "fazer", "haver", "ir", "manter",
				"obter", "pôr", "poder", "querer", "reter", "saber", "ser",
				"suster", "ter", "trazer", "ver", "vir", "crer", "ler", "medir",
				"ouvir", "pedir", "perder", "rir", "valer", "subir" };

		for (String verbs : irregularVerbs) {
			if (infinitive.equals(verbs)) {
				return VerbType.IRREGULAR;
			}
		}
		int verbLength = infinitive.length();
		if (isValidVerb(infinitive) && verbLength > 1) {
			String ending = infinitive.substring(verbLength - 2, verbLength);
			switch (ending) {
			case "ar":
				return VerbType.AR;
			case "er":
				return VerbType.ER;
			case "ir":
				return VerbType.IR;
			}
		}
		return null;

	}

	private static boolean isValidVerb(String infinitive) {
		if (infinitive == null) {
			return false;
		}
		infinitive = infinitive.toLowerCase();
		int verbLength = infinitive.length();
		return !infinitive.contains(" ") && !infinitive.contains("-")
				&& !infinitive.contains("+") && verbLength > 1
				&& infinitive.charAt(verbLength - 1) == 'r';
	}

	private static void checkForSpecialVerbs(String infinitive,
			VerbForm[] verbForms, String[][] verbsToReturn) {
		if (infinitive == null) {
			return;
		}
		switch (infinitive) {
		case "boiar":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "bóio", "bóias", "bóia",
							"boiamos", "boiais", "bóiam" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "bóie", "bóies", "bóie",
							"bóiemos", "bóieis", "bóiem" };
					break;
				}
			}
			break;
		case "recear":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "receio", "receias",
							"receia", "receamos", "receais", "receiam" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "receie", "receies",
							"receie", "receiemos", "receieis", "receiem" };
					break;
				}
			}
			break;
		case "odiar":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "odeio", "odeias",
							"odeia", "odiamos", "odiais", "odeiam" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "odeie", "odeies",
							"odeie", "odeiemos", "odeieis", "odeiem" };
					break;
				}
			}
			break;
		case "dar":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "dou", "dás", "dá",
							"damos", "dais", "dão" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "dei", "deste", "deu",
							"demos", "destes", "deram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "dera", "deras", "dera",
							"déramos", "déreis", "deram" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "dê", "dês", "dê",
							"demos", "deis", "dêem" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "desse", "desses",
							"desse", "déssemos", "déssei", "dessem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "der", "deres", "der",
							"dermos", "derdes", "derem" };
					break;
				}
			}
			break;
		case "dizer":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "digo", "dizes", "diz",
							"dizemos", "dizeis", "dizem" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "disse", "disseste",
							"disse", "dissemos", "dissetes", "disseram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "dissera", "disseras",
							"dissera", "disséramos", "disséreis", "disseram" };
					break;
				case FUT_IND:
					verbsToReturn[i] = new String[] { "direi", "dirás", "dirá",
							"diremos", "direis", "dirão" };
					break;
				case COND_IND:
					verbsToReturn[i] = new String[] { "diria", "dirias",
							"diria", "diríamos", "diríeis", "diriam" };
					break;
				}
			}
			break;
		case "estar":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "estou", "estás", "está",
							"estamos", "estais", "estão" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "estive", "estiveste",
							"esteve", "estivemos", "estivestes", "estiveram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "estivera", "estiveras",
							"estivera", "estivéramos", "estivéreis",
							"estivéram" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "esteja", "estejas",
							"esteja", "estejamos", "estejais", "estejam" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "estivesse", "estivesses",
							"estivesse", "estivéssemos", "estivésseis",
							"estivéssem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "estiver", "estiveres",
							"estiver", "estivermos", "estiverdes",
							"estiverem" };
					break;
				}
			}
			break;
		case "fazer":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "faço", "fazes", "faz",
							"fazemos", "fazeis", "fazem" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "fiz", "fizeste", "fez",
							"fizemos", "fizestes", "fizeram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "fizera", "fizeras",
							"fizera", "fizéramos", "fizéreis", "fizeram" };
					break;
				case FUT_IND:
					verbsToReturn[i] = new String[] { "farei", "farás", "fará",
							"faremos", "fareis", "farão" };
					break;
				case COND_IND:
					verbsToReturn[i] = new String[] { "faria", "farias",
							"faria", "faríamos", "faríeis", "fariam" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "faça", "faças", "faça",
							"façamos", "façais", "façam" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "fizesse", "fizesses",
							"fizesse", "fizéssemos", "fizésseis", "fizessem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "fizer", "fizeres",
							"fizer", "fizermos", "fizeres", "fizerem" };
					break;
				}
			}
			break;
		case "haver":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "hei", "hás", "há",
							"havemos", "haveis", "hão" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "houve", "houveste",
							"houve", "houvemos", "houvestes", "houveram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "houvera", "houveras",
							"houvera", "houvéramos", "houvéreis", "houveram" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "haja", "hajas", "haja",
							"hajamos", "hajais", "hajam" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "houvesse", "houvesses",
							"houvesse", "houvéssemos", "houvésseis",
							"houvessem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "houver", "houveres",
							"houver", "houvermos", "houverdes", "houverem" };
					break;
				}
			}
			break;
		case "ir":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "vou", "vais", "vai",
							"vamos", "ides", "vão" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "fui", "foste", "foi",
							"fomos", "fostes", "foram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "fora", "foras", "fora",
							"fôramos", "fôreis", "foram" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "vá", "vás", "vá",
							"vamos", "vades", "vão" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "fosse", "fosses",
							"fosse", "fôssemos", "fôsseis", "fossem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "for", "fores", "for",
							"formos", "fordes", "forem" };
					break;
				}
			}
			break;
		case "poder":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "posso", "podes", "pode",
							"podemos", "podeis", "podem" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "pude", "pudeste", "pôde",
							"pudemos", "pudestes", "puderam" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "pudera", "puderas",
							"pudera", "pudéramos", "pudéreis", "puderam" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "possa", "possas",
							"possa", "possamos", "possais", "possam" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "pudesse", "pudesses",
							"pudesse", "pudéssemos", "pudésseis", "pudessem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "puder", "puderes",
							"puder", "pudermos", "puderdes", "puderem" };
					break;
				}
			}
			break;
		case "pôr":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "ponho", "pões", "põe",
							"pomos", "pondes", "põem" };
					break;
				case IMP_IND:
					verbsToReturn[i] = new String[] { "punha", "punhas",
							"punha", "púnhamos", "púnheis", "punham" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "pus", "puseste", "pôs",
							"pusemos", "pusestes", "puseram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "pusera", "puseras",
							"pusera", "puséramos", "puséreis", "puseram" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "ponha", "ponhas",
							"ponha", "ponhamos", "ponhais", "ponham" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "pusesse", "pusesses",
							"pusesse", "puséssemos", "pusésseis", "pusessem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "puser", "puseres",
							"puser", "pusermos", "puserdes", "puserem" };
					break;
				}
			}
			break;
		case "querer":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "quero", "queres", "quer",
							"queremos", "quereis", "querem" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "quis", "quiseste",
							"quis", "quisemos", "quisestes", "quiseram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "quisera", "quiseras",
							"quisera", "quiséramos", "quiséreis", "quiseram" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "queira", "queiras",
							"queira", "queiramos", "queirais", "queiram" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "quisesse", "quisesses",
							"quisesse", "quiséssemos", "quisésseis",
							"quisessem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "quiser", "quiseres",
							"quiser", "quisermos", "quiserdes", "quiserem" };
					break;
				}
			}
			break;
		case "saber":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "sei", "sabes", "sabe",
							"sabemos", "sabeis", "sabem" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "soube", "soubeste",
							"soube", "soubemos", "soubestes", "souberam" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "soubera", "souberas",
							"soubera", "soubéramos", "soubéreis", "souberam" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "saiba", "saibas",
							"saiba", "saibamos", "saibais", "saibam" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "soubesse", "soubesses",
							"soubesse", "soubéssemos", "soubésseis",
							"soubessem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "souber", "souberes",
							"souber", "soubermos", "souberdes", "souberem" };
					break;
				}
			}
			break;
		case "ser":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "sou", "és", "é", "somos",
							"sois", "são" };
					break;
				case IMP_IND:
					verbsToReturn[i] = new String[] { "era", "eras", "era",
							"éramos", "éreis", "eram" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "fui", "foste", "foi",
							"fomos", "fostes", "foram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "fora", "foras", "fora",
							"fôramos", "fôreis", "foram" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "seja", "sejas", "seja",
							"sejamos", "sejais", "sejam" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "fosse", "fosses",
							"fosse", "fôssemos", "fôsseis", "fossem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "for", "fores", "for",
							"formos", "fordes", "forem" };
					break;
				}
			}
			break;
		case "abster":
		case "ater":
		case "conter":
		case "deter":
		case "entreter":
		case "manter":
		case "obter":
		case "reter":
		case "suster":
		case "ter":
			String prefix = "";
			if (!infinitive.equals("ter")) {
				prefix = infinitive.split("ter")[0];
			}
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { prefix + "tenho",
							prefix + "tens", prefix + "tem", prefix + "temos",
							prefix + "tendes", prefix + "têm" };
					break;
				case IMP_IND:
					verbsToReturn[i] = new String[] { prefix + "tinha",
							prefix + "tinhas", prefix + "tinha",
							prefix + "tínhamos", prefix + "tínheis",
							prefix + "tinham" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { prefix + "tive",
							prefix + "tiveste", prefix + "tive",
							prefix + "tivemos", prefix + "tivestes",
							prefix + "tiveram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { prefix + "tivera",
							prefix + "tiveras", prefix + "tivera",
							prefix + "tivéramos", prefix + "tivéreis",
							prefix + "tiveram" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { prefix + "tenha",
							prefix + "tenhas", prefix + "tenha",
							prefix + "tenhamos", prefix + "tenhais",
							prefix + "tenham" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { prefix + "tivesse",
							prefix + "tivesses", prefix + "tivesse",
							prefix + "tivéssemos", prefix + "tivésseis",
							prefix + "tivessem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { prefix + "tiver",
							prefix + "tiveres", prefix + "tiver",
							prefix + "tivermos", prefix + "tiverdes",
							prefix + "tiverem" };
					break;
				}
			}
			break;
		case "trazer":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "trago", "trazes", "traz",
							"trazemos", "trazeis", "trazem" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "trouxe", "trouxes",
							"trouxe", "trouxemos", "trouxestes", "trouxeram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "trouxera", "trouxeras",
							"trouxera", "trouxéramos", "trouxéreis",
							"trouxeram" };
					break;
				case FUT_IND:
					verbsToReturn[i] = new String[] { "trarei", "trarás",
							"trará", "traremos", "trareis", "trarão" };
					break;
				case COND_IND:
					verbsToReturn[i] = new String[] { "traria", "trarias",
							"traria", "traríamos", "traríeis", "trariam" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "traga", "tragas",
							"traga", "tragamos", "tragais", "tragam" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "trouxesse", "trouxesses",
							"trouxesse", "trouxéssemos", "trouxésseis",
							"trouxessem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "trouxer", "trouxeres",
							"trouxer", "trouxermos", "trouxerdes",
							"trouxerem" };
					break;
				}
			}
			break;
		case "ver":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "vejo", "vês", "vê",
							"vemos", "vedes", "vêem" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "vi", "viste", "viu",
							"vimos", "vistes", "viram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "vira", "viras", "vira",
							"víramos", "víreis", "viram" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "veja", "vejas", "veja",
							"vejamos", "vírais", "vejam" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "visse", "visses",
							"visse", "víssemos", "vísseis", "vissem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "vir", "vires", "vir",
							"virmos", "virdes", "virem" };
					break;
				}
			}
			break;
		case "vir":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "venho", "vens", "vem",
							"vimos", "vindes", "vêm" };
					break;
				case IMP_IND:
					verbsToReturn[i] = new String[] { "vinha", "vinhas",
							"vinha", "vínhamos", "vínheis", "vinham" };
					break;
				case PRET_IND:
					verbsToReturn[i] = new String[] { "vim", "vieste", "veio",
							"veimos", "viestes", "vieram" };
					break;
				case SIMP_PLUP_IND:
					verbsToReturn[i] = new String[] { "viera", "vieras",
							"viera", "viéramos", "viéreis", "vieram" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "venha", "vehnas",
							"venha", "venhamos", "venhais", "venham" };
					break;
				case IMP_SUBJ:
					verbsToReturn[i] = new String[] { "viesse", "viesses",
							"viesse", "viéssemos", "viéssemos", "viessem" };
					break;
				case FUT_SUBJ:
					verbsToReturn[i] = new String[] { "vier", "vieres", "vier",
							"viermos", "vierdes", "vierem" };
					break;
				}
			}
			break;
		case "crer":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "creio", "crês", "crê",
							"cremos", "credes", "crêem" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "creia", "creias",
							"creia", "creiamos", "creiais", "creiam" };
					break;
				}
			}
			break;
		case "ler":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "leio", "lês", "lê",
							"lemos", "ledes", "lêem" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "leia", "leias", "leia",
							"leiamos", "leiais", "leiam" };
					break;
				}
			}
			break;
		case "medir":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "meço", "medes", "mede",
							"medimos", "medis", "medem" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "meça", "meças", "meça",
							"meçamos", "meçais", "meçam" };
					break;
				}
			}
			break;
		case "ouvir":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "ouço", "ouves", "ouve",
							"ouvimos", "ouvis", "ouvem" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "ouça", "ouças", "ouça",
							"ouçamos", "ouçais", "ouçam" };
					break;
				}
			}
			break;
		case "pedir":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "peço", "pedes", "pede",
							"pedimos", "pedis", "pedem" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "peça", "peças", "peça",
							"peçamos", "peçais", "peçam" };
					break;
				}
			}
			break;
		case "perder":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "perco", "perdes",
							"perde", "perdemos", "perdeis", "perdem" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "perca", "percas",
							"perca", "percamos", "percais", "percam" };
					break;
				}
			}
			break;
		case "rir":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "rio", "ris", "ri",
							"rimos", "rides", "riem" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "ria", "rias", "ria",
							"riamos", "riais", "riam" };
					break;
				}
			}
			break;
		case "valer":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "valho", "vales", "vale",
							"valemos", "valeis", "valem" };
					break;
				case PRES_SUBJ:
					verbsToReturn[i] = new String[] { "valha", "valhas",
							"valha", "valhamos", "valhais", "valham" };
					break;
				}
			}
			break;
		case "subir":
			for (int i = 0; i < verbForms.length; i++) {
				VerbForm verbForm = verbForms[i];
				switch (verbForm) {
				case PRES_IND:
					verbsToReturn[i] = new String[] { "subo", "sobes", "sobe",
							"subimos", "subis", "sobem" };
					break;
				}
			}
			break;
		}
	}

	private static String getGerund(String infinitive) {
		if (infinitive == null) {
			return null;
		}
		infinitive = infinitive.toLowerCase();
		int verbLength = infinitive.length();
		if (!infinitive.contains(" ") && !infinitive.contains("-")
				&& verbLength > 1) {
			String ending = infinitive.substring(verbLength - 2, verbLength);
			if (ending.equals("ar") || ending.equals("ir")
					|| ending.equals("er")) {
				return infinitive.substring(0, verbLength - 1) + "ndo";
			} else if (infinitive.toLowerCase().equals("pôr")) {
				return "pondo";
			}
		}
		return null;
	}

	private static String getParticiple(String infinitive) {
		if (infinitive == null) {
			return null;
		}
		infinitive = infinitive.toLowerCase();
		String regParticiple = getRegularParticiple(infinitive);
		if (regParticiple == null) {
			return getIrregularParticiple(infinitive);
		}
		return regParticiple;
	}

	private static String getIrregularParticiple(String infinitive) {
		if (infinitive == null) {
			return null;
		}
		infinitive = infinitive.toLowerCase();
		switch (infinitive) {
		case "abrir":
			return "aberto";
		case "aceitar":
			return "aceito";
		case "acender":
			return "aceso";
		case "entregar":
			return "entregue";
		case "enxugar":
			return "enxuto";
		case "escrever":
			return "escrito";
		case "expulsar":
			return "expulso";
		case "ganhar":
			return "ganho";
		case "gastar":
			return "gasto";
		case "limpar":
			return "limpo";
		case "matar":
			return "morto";
		case "omitir":
			return "omisso";
		case "pagar":
			return "pago";
		case "prender":
			return "preso";
		case "romper":
			return "roto";
		case "soltar":
			return "solto";
		case "suspender":
			return "suspenso";
		case "fazer":
			return "feito";
		case "pôr":
			return "posto";
		case "ver":
			return "visto";
		case "dizer":
			return "dito";
		default:
			return null;
		}
	}

	private static String getRegularParticiple(String infinitive) {
		if (infinitive == null) {
			return null;
		}
		int verbLength = infinitive.length();
		if (!infinitive.contains(" ") && !infinitive.contains("-")
				&& verbLength > 1 && !infinitive.equals("abrir")
				&& !infinitive.equals("escrever")
				&& !infinitive.equals("ganhar") && !infinitive.equals("gastar")
				&& !infinitive.equals("pagar") && !infinitive.equals("fazer")
				&& !infinitive.equals("pôr") && !infinitive.equals("ver")
				&& !infinitive.equals("dizer")) {
			String ending = infinitive.substring(verbLength - 2, verbLength);
			if (ending.equals("ar")) {
				return infinitive.substring(0, verbLength - 1) + "do";
			} else if (ending.equals("ir") || ending.equals("er")) {
				return infinitive.substring(0, verbLength - 2) + "ido";
			}
		}
		return null;
	}
}
