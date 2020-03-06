function processAll(isSingleVerb, prefixToRemove, table) {
//   let table = `Infinitive
// Impersonal	fazer
// Personal	fazer	fazeres	fazer	fazermos	fazerdes	fazerem
// Gerund
// fazendo
// Past participle
// Masculine	feito	feitos
// Feminine	feita	feitas
// Indicative
// Present	faço	fazes	faz	fazemos	fazeis	fazem
// Imperfect	fazia	fazias	fazia	fazíamos	fazíeis	faziam
// Preterite	fiz	fizeste	fez	fizemos	fizestes	fizeram
// Pluperfect	fizera	fizeras	fizera	fizéramos	fizéreis	fizeram
// Future	farei	farás	fará	faremos	fareis	farão
// Conditional
// faria	farias	faria	faríamos	faríeis	fariam
// Subjunctive
// Present	faça	faças	faça	façamos	façais	façam
// Imperfect	fizesse	fizesses	fizesse	fizéssemos	fizésseis	fizessem
// Future	couber	couberes	couber	coubermos	couberdes	couberem
// Imperative
// Affirmative	-	cabe	caiba	caibamos	cabei	caibam
// Negative (não)	-	caibas	caiba	caibamos	caibais	caibam`;

  //let isSingleVerb = true;
  let prefix = isSingleVerb ? "" : "stem + ";

  let infinitive = table.match(/(?<=Impersonal\t)(\S*)/g)[0].trim();
  let persInfs = table
    .match(/(?<=Personal\t)([\S\t]*)/g)[0]
    .trim()
    .split("\t");

  let gerund = table.match(/(?<=Gerund\n)(\S*)/g)[0].trim();
  let participle = table
    .match(/(?<=Past participle\nMasculine\t)(\S*)/g)[0]
    .trim();

  let generatedGerund = infinitive.substring(0, infinitive.length - 1) + "ndo";
  let ending = infinitive.substring(infinitive.length - 2, infinitive.length);
  let generatedPart =
    infinitive.substring(0, infinitive.length - 2) +
    (ending === "ar" ? "ado" : "ido");
let introString = "";


  if (generatedGerund !== gerund || generatedPart !== participle) {
    introString +=
      `\t\t\tgerund = ${prefix}"${replacePrefixWithNothing(gerund, prefixToRemove)}";\n\t\t\tparticiple = ${prefix}"${replacePrefixWithNothing(participle, prefixToRemove)}";\n`;
  }



  let preses = table.match(/(?<=Present\t)(.*)/g);
  let imperfs = table.match(/(?<=Imperfect\t)(.*)/g);
  let futs = table.match(/(?<=Future\t)(.*)/g);

  let presInd = preses[0].trim().split("\t");
  let impInd = imperfs[0].trim().split("\t");
  let pretInd = table
    .match(/(?<=Preterite\t)(.*)/g)[0]
    .trim()
    .split("\t");
  let plupInd = table
    .match(/(?<=Pluperfect\t)(.*)/g)[0]
    .trim()
    .split("\t");
  let futInd = futs[0].trim().split("\t");
  let cond = table
    .match(/(?<=Conditional\n)(.*)/g)[0]
    .trim()
    .split("\t");

  let futIndStem = futInd[0].substring(0, futInd[0].length - 2);
  let conIndStem = cond[0].substring(0, cond[0].length - 2);
  if (futIndStem !== conIndStem) {
    console.log("ANALYZE FUT AND COND IND - missing futAndCondStem");
  } else if (futIndStem !== infinitive) {
    introString += `\t\t\tfutAndCondStem = ${prefix}"${replacePrefixWithNothing(futIndStem, prefixToRemove)}";\n`
  }
  let futIndEndings = ["ei", "ás", "á", "emos", "eis", "ão"];
  let condEndings = ["ia", "ias", "ia", "íamos", "íeis", "iam"];
  let generatedfutInd = futIndEndings.map(end => {
    return futIndStem + end;
  });
  let generatedCond = condEndings.map(end => {
    return conIndStem + end;
  });
  compareList(futInd, generatedfutInd, "ERROR WITH FUT IND");
  compareList(cond, generatedCond, "ERROR WITH COND");

  let presSubj = preses[1].trim().split("\t");
  let presSubjStem = presSubj[0].substring(0, presSubj[0].length - 1);
  let presSubjEnd = presSubj[0].charAt(presSubj[0].length - 1);
  if (ending === "ar" && presSubjEnd !== "e") {
    introString += "\t\t\tisArEndings = false;\n\t\t\tisArEndingsSet = true;\n";
  } else if (ending !== "ar" && presSubjEnd !== "a") {
    introString += "\t\t\tisArEndings = true;\n\t\t\tisArEndingsSet = true;\n";
  }
  introString += `\t\t\tpresSubjStem = ${prefix}"${replacePrefixWithNothing(presSubjStem, prefixToRemove)}";\n`;

  let subjEndAr = ["e", "es", "e", "emos", "eis", "em"];
  let subjEndNotAr = ["a", "as", "a", "amos", "ais", "am"];
  let selectedPresSubjEnds = ending === "ar" ? subjEndAr : subjEndNotAr;
  let generatedPresSubj = selectedPresSubjEnds.map(end => {
    return presSubjStem + end;
  });
  compareList(presSubj, generatedPresSubj, "ERROR WITH PRES SUBJ");

  let impSubj = imperfs[1].trim().split("\t");
  let impSubjStem = impSubj[0].substring(0, impSubj[0].length - 4);
  let impSubjEndings = impSubj.map(word => {
    return word.substring(impSubjStem.length, word.length);
  });
  let generatedImpSubj = impSubjEndings.map(ending => {
    return impSubjStem + ending;
  });
  compareList(impSubj, generatedImpSubj, "ERROR WITH IMP SUBJ");
  introString += `\t\t\timpSubjStem = ${prefix}"${replacePrefixWithNothing(impSubjStem, prefixToRemove)}";\n`;

  let futSubj = futs[1].trim().split("\t");
  if (futSubj[0] !== infinitive) {
      
  introString += `\t\t\tfutSubjStem = ${prefix}"${replacePrefixWithNothing(futSubj[0], prefixToRemove)}";\n`;
  }
  let futSubjEndAndPersonalInfinitiveEndings = [
    "",
    "es",
    "",
    "mos",
    "des",
    "em"
  ];
  let generatedFutSubj = futSubjEndAndPersonalInfinitiveEndings.map(end => {
    return futSubj[0] + end;
  });
  compareList(futSubj, generatedFutSubj, "ERROR WITH FUTURE SUBJ");

  let impAff = table
    .match(/(?<=Affirmative\t)(.*)/g)[0]
    .trim()
    .split("\t");
  let impNeg = table
    .match(/(?<=Negative \(não\)\t)(.*)/g)[0]
    .trim()
    .split("\t");

  impNeg[0] = null;
  let generatedImpNeg = presSubj.slice(0);
  generatedImpNeg[0] = null;
  compareList(impNeg, generatedImpNeg, "IMP NEG ERR");

  let generatedPersInfEndings = futSubjEndAndPersonalInfinitiveEndings.map(
    end => {
      return infinitive + end;
    }
  );
  compareList(persInfs, generatedPersInfEndings, "IMP NEG ERR");

  console.log("\nIntro:\n");
  console.log(introString + "\n");

  console.log("\nconjugatePresInd: \n");
  let arr = presInd;
  arr = arr.map(word => {return replacePrefixWithNothing(word, prefixToRemove)})
  console.log(
    `\t\t\treturn new String[]{ ${prefix}"${arr[0]}", ${prefix}"${arr[1]}", ${prefix}"${arr[2]}", ${prefix}"${arr[3]}", ${prefix}"${arr[4]}", ${prefix}"${arr[5]}" };\n`.replace(
      /\s\+\s""/g,
      ""
    )
  );

  console.log("\nconjugateImpInd: \n");
  arr = impInd;
  arr = arr.map(word => {return replacePrefixWithNothing(word, prefixToRemove)})
  console.log(
    `\t\t\treturn new String[]{ ${prefix}"${arr[0]}", ${prefix}"${arr[1]}", ${prefix}"${arr[2]}", ${prefix}"${arr[3]}", ${prefix}"${arr[4]}", ${prefix}"${arr[5]}" };\n`.replace(
      /\s\+\s""/g,
      ""
    )
  );

  console.log("\nconjugatePretInd: \n");
  arr = pretInd;
  arr = arr.map(word => {return replacePrefixWithNothing(word, prefixToRemove)})
  console.log(
    `\t\t\treturn new String[]{ ${prefix}"${arr[0]}", ${prefix}"${arr[1]}", ${prefix}"${arr[2]}", ${prefix}"${arr[3]}", ${prefix}"${arr[4]}", ${prefix}"${arr[5]}" };\n`.replace(
      /\s\+\s""/g,
      ""
    )
  );

  console.log("\nconjugateSimpPlupInd: \n");
  arr = plupInd;
  arr = arr.map(word => {return replacePrefixWithNothing(word, prefixToRemove)})
  console.log(
    `\t\t\treturn new String[]{ ${prefix}"${arr[0]}", ${prefix}"${arr[1]}", ${prefix}"${arr[2]}", ${prefix}"${arr[3]}", ${prefix}"${arr[4]}", ${prefix}"${arr[5]}" };\n`.replace(
      /\s\+\s""/g,
      ""
    )
  );

  console.log("\nconjugateImpSubj: \n");
  arr = impSubjEndings;
  arr = arr.map(word => {return replacePrefixWithNothing(word, prefixToRemove)})
  console.log(
    `\t\t\treturn new String[]{ impSubjStem + "${arr[0]}", impSubjStem + "${arr[1]}", impSubjStem + "${arr[2]}", impSubjStem + "${arr[3]}", impSubjStem + "${arr[4]}", impSubjStem +"${arr[5]}" };\n`.replace(
      /\s\+\s""/g,
      ""
    )
  );

  console.log("\nconjugateFutSubj: \n");
  arr = futSubjEndAndPersonalInfinitiveEndings;
  arr = arr.map(word => {return replacePrefixWithNothing(word, prefixToRemove)})
  console.log(
    `\t\t\treturn new String[]{ futSubjStem + "${arr[0]}", futSubjStem + "${arr[1]}", futSubjStem + "${arr[2]}", futSubjStem + "${arr[3]}", futSubjStem + "${arr[4]}", futSubjStem +"${arr[5]}" };\n`.replace(
      /\s\+\s""/g,
      ""
    )
  );

  console.log("\nconjugateImpAff: \n");
  arr = impAff;
  arr = arr.map(word => {return replacePrefixWithNothing(word, prefixToRemove)})
  console.log(
    `\t\t\treturn new String[]{ null, ${prefix}"${arr[1]}", ${prefix}"${arr[2]}", ${prefix}"${arr[3]}", ${prefix}"${arr[4]}", ${prefix}"${arr[5]}" };\n`.replace(
      /\s\+\s""/g,
      ""
    )
  );

  console.log("\nconjugatePersInf: \n");
  arr = futSubjEndAndPersonalInfinitiveEndings;
  arr = arr.map(word => {return replacePrefixWithNothing(word, prefixToRemove)})
  console.log(
    `\t\t\treturn new String[]{ infinitive + "${arr[0]}", infinitive + "${arr[1]}", infinitive + "${arr[2]}", infinitive + "${arr[3]}", infinitive + "${arr[4]}", infinitive + "${arr[5]}" };\n`.replace(/\s\+\s""/g,""));

}

function compareList(tenseFromTable, generatedTense, error) {
  let same = true;
  for (let i = 0; i < generatedTense.length - 1; i++) {
    if (generatedTense[i] !== tenseFromTable[i]) {
      same = false;
    }
  }
  if (!same) {
    console.log(error + "\n");
  }
}

function replacePrefixWithNothing(str, pref) {
    if (pref === "" || pref === null || pref === undefined) {
        return str;
    }
    else {
        return str.replace(pref, "");
    }
}

// ODIR_ATIR, OLIR

let group = `AIR, OLIR, ODIR_ATIR, ABRIR, TER, VIR, FAZER,
IAR, POR, DIZER, CABER, SEGUIR, CRER, COBRIR, DAR, ESTAR, HAVER,

GEAR, NEVAR

IR, OUVIR, PERDER, PODER, PROVER, SABER,
SER, TOSSIR, TRAZER, ENGOLIR, FUGIR, EDIR, DIVERTIR,
SERVIR, SAUDAR, REUNIR, REMIR, 
DORMIR, PÔR,

LER, QUERER, VER, RIR, VALER, GREDIR, EDIR, ENTIR, ELIR,
REG_AR, REG_ER, REG_IR, EAR, OIBIR, PREVENIR, ERIR,
OER, OIAR, VESTIR, TUIR, BUIR, STRUIR, ERZIR`

const table = `

`;

processAll(true, null, table);
