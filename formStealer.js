// @ts-check

// WP: With Prefix
/**
 * @param {string} verbClass
 * @param {string} verbsInGroup
 * @param {string} table
 * @param {string} tableWP
 */
function getVerbGroup(verbClass, verbsInGroup, table, tableWP) {
    // Infinitives
    let infinitive = table
        .match(/(?<=Impersonal[\t|\n])([\n\s\S]*?)(?=Personal)/g)[0]
        .trim();
    let infinitiveWP = tableWP
        .match(/(?<=Impersonal[\t|\n])([\n\s\S]*?)(?=Personal)/g)[0]
        .trim();

    if (infinitiveWP.length <= infinitive.length) {
        const tempTable = tableWP;
        tableWP = table;
        table = tempTable;

        const tempInfinitive = infinitiveWP;
        infinitiveWP = infinitive;
        infinitive = tempInfinitive;
    }

    //matches(infinitive, verbLength, "fazer")

    let groupIfStatement = `if (${
        verbsInGroup !== null
            ? `infinitive.matches("${verbsInGroup}")`
            : `matches(infinitive, verbLength, "${infinitive}")`
    }) {
\t\t\tverbClass = VerbClass.${verbClass};
\t\t\tstem = infinitive.substring(0, verbLength - ${infinitive.length});\n`;

    // Start of the first area

    //prefix to remove from the verbs with prefixes
    const prefixToRemove = infinitiveWP.substring(
        0,
        infinitiveWP.length - infinitive.length
    );

    // Personal Infinitives
    const persInfs = table
        .match(/(?<=Personal[\t|\n])([\n\s\S]*?)(?=Gerund)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const persInfsWP = tableWP
        .match(/(?<=Personal[\t|\n])([\n\s\S]*?)(?=Gerund)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');

    // Gerunds
    const gerund = table
        .match(/(?<=Gerund[\t|\n])([\n\s\S]*?)(?=Past participle)/g)[0]
        .trim();
    const gerundWP = tableWP
        .match(/(?<=Gerund[\t|\n])([\n\s\S]*?)(?=Past participle)/g)[0]
        .trim();

    // Participles
    const participle = table
        .match(/(?<=Past participle\nMasculine\t)(\S*)/g)[0]
        .trim();
    const participleWP = tableWP
        .match(/(?<=Past participle\nMasculine\t)(\S*)/g)[0]
        .trim();

    // Endings
    const ending = infinitive.substring(
        infinitive.length - 2,
        infinitive.length
    );
    const endingWP = infinitiveWP.substring(
        infinitiveWP.length - 2,
        infinitiveWP.length
    );

    // Gerund check
    const generatedGerund =
        infinitive.substring(0, infinitive.length - 1) + 'ndo';
    const generatedGerundWP =
        infinitiveWP.substring(0, infinitiveWP.length - 1) + 'ndo';

    // Participle check
    const generatedPart =
        infinitive.substring(0, infinitive.length - 2) +
        (ending === 'ar' ? 'ado' : 'ido');
    const generatedPartWP =
        infinitiveWP.substring(0, infinitiveWP.length - 2) +
        (endingWP === 'ar' ? 'ado' : 'ido');

    if (generatedGerund !== gerund || generatedPart !== participle) {
        if (prefixToRemove + gerund !== gerundWP) {
            throw `error with gerund of ${infinitive}/${infinitiveWP}: ${gerund}/${gerundWP}`;
        }
        if (prefixToRemove + participle !== participleWP) {
            throw `error with participle of ${infinitive}/${infinitiveWP}: ${participle}/${participleWP}`;
        }
        groupIfStatement += `\t\t\tgerund = stem + "${gerund}";
\t\t\tparticiple = stem + "${participle}";\n`;
    } else if (generatedGerundWP !== gerundWP) {
        throw `error with gerund of ${infinitiveWP}: actual ${generatedGerundWP} generated ${gerundWP}`;
    } else if (generatedPartWP !== participleWP) {
        throw `error with participle of ${infinitiveWP}: actual ${generatedPartWP} generated ${participleWP}`;
    }

    // Present tenses
    const preses = table.match(/(?<=Present[\t|\n])([\n\s\S]*?)(?=Imperfect)/g);
    const presesWP = tableWP.match(
        /(?<=Present[\t|\n])([\n\s\S]*?)(?=Imperfect)/g
    );

    // Present Indicative
    const presInd = preses[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const presIndWP = presesWP[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const generatedPresIndWP = presInd.map(word =>
        word
            .split('/')
            .map(word1 => prefixToRemove + word1)
            .join('/')
    );
    const presIndValidFormula = compareList(
        presIndWP,
        generatedPresIndWP,
        `error with {stem + presInd} formula for ${infinitiveWP}`
    );
    const presIndWPEndings = presIndWP.map((word, index) =>
        word.substring(word.length - presInd[index].length, word.length)
    );

    // Imperfect Indicative
    const impInd = table
        .match(/(?<=Imperfect[\t|\n])([\n\s\S]*?)(?=Preterite)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const impIndWP = tableWP
        .match(/(?<=Imperfect[\t|\n])([\n\s\S]*?)(?=Preterite)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const generatedImpIndWP = impInd.map(word =>
        word
            .split('/')
            .map(word1 => prefixToRemove + word1)
            .join('/')
    );
    const impIndValidFormula = compareList(
        impIndWP,
        generatedImpIndWP,
        `error with {stem + impInd} formula for ${infinitiveWP}`
    );
    const impIndWPEndings = impIndWP.map((word, index) =>
        word.substring(word.length - impInd[index].length, word.length)
    );

    // Preterite Indicative
    const pretInd = table
        .match(
            /(?<=Preterite[\t|\n])(((?!Preterite)[.|\s|\S])*)(?=Pluperfect)/g
        )[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const pretIndWP = tableWP
        .match(
            /(?<=Preterite[\t|\n])(((?!Preterite)[.|\s|\S])*)(?=Pluperfect)/g
        )[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const generatedPretIndWP = pretInd.map(word =>
        word
            .split('/')
            .map(word1 => prefixToRemove + word1)
            .join('/')
    );
    const pretIndValidFormula = compareList(
        pretIndWP,
        generatedPretIndWP,
        `error with {stem + pretInd} formula for ${infinitiveWP}`
    );
    const pretIndWPEndings = pretIndWP.map((word, index) =>
        word.substring(word.length - pretInd[index].length, word.length)
    );

    // Pluperfect Indicative
    const plupInd = table
        .match(
            /(?<=Pluperfect[\t|\n])(((?!Pluperfect)[.|\s|\S])*?)(?=Future)/g
        )[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const plupIndWP = tableWP
        .match(
            /(?<=Pluperfect[\t|\n])(((?!Pluperfect)[.|\s|\S])*?)(?=Future)/g
        )[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const generatedPlupIndWP = plupInd.map(word =>
        word
            .split('/')
            .map(word1 => prefixToRemove + word1)
            .join('/')
    );
    const plupIndValidFormula = compareList(
        plupIndWP,
        generatedPlupIndWP,
        `error with {stem + plupInd} formula for ${infinitiveWP}`
    );
    const plupIndWPEndings = plupIndWP.map((word, index) =>
        word.substring(word.length - plupInd[index].length, word.length)
    );

    // Future Indicative
    const futInd = table
        .match(/(?<=Future[\t|\n])([\n\s\S]*?)(?=Conditional)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const futIndWP = tableWP
        .match(/(?<=Future[\t|\n])([\n\s\S]*?)(?=Conditional)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');

    // Conditional
    const cond = table
        .match(
            /(?<=Conditional[\t|\n])(((?!Conditional)[.|\s|\S])*)(?=Subjunctive)/g
        )[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const condWP = tableWP
        .match(
            /(?<=Conditional[\t|\n])(((?!Conditional)[.|\s|\S])*)(?=Subjunctive)/g
        )[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');

    // Future Indicative Stem
    const futIndStem = futInd[0].substring(0, futInd[0].length - 2);
    const futIndStemWP = futIndWP[0].substring(0, futIndWP[0].length - 2);
    if (prefixToRemove + futIndStem !== futIndStemWP) {
        throw `error with future indicative stem for ${infinitive}/${infinitiveWP}: ${futIndStem}/${futIndStemWP}`;
    }

    // Conditional Stem
    const condStem = cond[0].substring(0, cond[0].length - 2);
    const condStemWP = condWP[0].substring(0, condWP[0].length - 2);
    if (prefixToRemove + condStem !== condStemWP) {
        throw `error with conditional stem for ${infinitive}/${infinitiveWP}: ${condStem}/${condStemWP}`;
    }

    // Future Indicative Stem matches Conditional Stem check
    if (futIndStem !== condStem) {
        throw `error with future indicative and conditional stems for ${infinitive}: fut: ${futIndStem}, cond: ${condStem}`;
    }

    // if future indicative is different from the infinitive, add intro line for it
    if (futIndStem !== infinitive) {
        groupIfStatement += `\t\t\tfutAndCondStem = stem + "${futIndStem}";\n`;
    }

    // validate future indicative
    const futIndEndings = ['ei', 'ás', 'á', 'emos', 'eis', 'ão'];
    const generatedfutInd = futIndEndings.map(end => {
        return futIndStem + end;
    });
    const generatedfutIndWP = futIndEndings.map(end => {
        return futIndStemWP + end;
    });
    const generatedfutIndWP2 = futIndEndings.map(end => {
        return prefixToRemove + futIndStem + end;
    });
    compareList(
        futInd,
        generatedfutInd,
        `error with future indicative endings for ${infinitive}`
    );
    compareList(
        futIndWP,
        generatedfutIndWP,
        `error with future indicative endings for ${infinitiveWP}`
    );
    compareList(
        futIndWP,
        generatedfutIndWP2,
        `error with {stem + future indicative stem + endings} formula for ${infinitiveWP}`
    );

    // validate conditional
    const condEndings = ['ia', 'ias', 'ia', 'íamos', 'íeis', 'iam'];
    const generatedCond = condEndings.map(end => {
        return condStem + end;
    });
    const generatedCondWP = condEndings.map(end => {
        return condStemWP + end;
    });
    const generatedCondWP2 = condEndings.map(end => {
        return prefixToRemove + condStem + end;
    });
    compareList(
        cond,
        generatedCond,
        `error with conditional endings for ${infinitive}`
    );
    compareList(
        condWP,
        generatedCondWP,
        `error with conditional endings for ${infinitiveWP}`
    );
    compareList(
        condWP,
        generatedCondWP2,
        `error with {stem + conditional stem + endings} formula for ${infinitiveWP}`
    );

    // Present Subjunctive
    const presSubj = preses[1]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const presSubjWP = presesWP[1]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');

    // Present Subjunctive Stem
    const presSubjStem = presSubj[0].substring(0, presSubj[0].length - 1);
    const presSubjStemWP = presSubjWP[0].substring(0, presSubjWP[0].length - 1);
    if (prefixToRemove + presSubjStem !== presSubjStemWP) {
        throw `error with present subjunctive stem for ${infinitive}/${infinitiveWP}: ${presSubjStem}/${presSubjStemWP}`;
    }

    const subjEndAr = ['e', 'es', 'e', 'emos', 'eis', 'em'];
    const subjEndNotAr = ['a', 'as', 'a', 'amos', 'ais', 'am'];
    const subjEndDar = ['ê', 'ês', 'ê', 'emos', 'eis', 'eem'];
    let isArEndings = ending === 'ar';
    let presSubjEnds = isArEndings ? subjEndAr : subjEndNotAr;

    const presSubjEnd = presSubj[0].charAt(presSubj[0].length - 1);
    if (ending === 'ar' && presSubjEnd !== 'e') {
        if (infinitive === 'dar') {
            presSubjEnds = subjEndDar;
        } else {
            presSubjEnds = subjEndNotAr;
        }
    } else if (ending !== 'ar' && presSubjEnd !== 'a') {
        isArEndings = true;
    }
    groupIfStatement += `\t\t\tpresSubjStem = stem + "${presSubjStem}";\n`;
    const impNegEnds = presSubjEnds.slice(0);
    impNegEnds[0] = null;

    // Present subjunctive validation

    const generatedPresSubj = presSubjEnds.map(end => {
        return presSubjStem + end;
    });
    const generatedPresSubjWP = presSubjEnds.map(end => {
        return presSubjStemWP + end;
    });
    const generatedPresSubjWP2 = presSubjEnds.map(end => {
        return prefixToRemove + presSubjStem + end;
    });
    compareList(
        presSubj,
        generatedPresSubj,
        `error with present subjunctive endings for ${infinitive}`
    );
    compareList(
        presSubjWP,
        generatedPresSubjWP,
        `error with present subjunctive for ${infinitiveWP}`
    );
    compareList(
        presSubjWP,
        generatedPresSubjWP2,
        `error with {stem + present subjunctive stem + endings} formula for ${infinitiveWP}`
    );

    // Imperfect Subjunctive
    const impSubj = table
        .match(/(?<=Imperfect[\t|\n])([\n\s\S]*?)(?=Future)/g)[1]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const impSubjWP = tableWP
        .match(/(?<=Imperfect[\t|\n])([\n\s\S]*?)(?=Future)/g)[1]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');

    // Imperfect Subjunctive Stems
    const impSubjStem = impSubj[0].substring(0, impSubj[0].length - 4);
    const impSubjStemWP = impSubjWP[0].substring(0, impSubjWP[0].length - 4);
    if (prefixToRemove + impSubjStem !== impSubjStemWP) {
        throw `error with imperfect subjunctive stem for ${infinitive}/${infinitiveWP}: ${impSubjStem}/${impSubjStemWP}`;
    }

    // Imperfect Subjunctive Endings
    const /**
         * @param {string} word
         */
        impSubjEndings = impSubj.map(word => {
            return word.substring(impSubjStem.length, word.length);
        });
    const /**
         * @param {string} word
         */
        impSubjEndingsWP = impSubjWP.map(word => {
            return word.substring(impSubjStemWP.length, word.length);
        });
    compareList(
        impSubjEndings,
        impSubjEndingsWP,
        `imperfect subjunctive endings don't match for ${infinitive}/${infinitiveWP}`
    );

    // Imperfect Subjunctive validation
    const /**
         * @param {any} ending
         */
        generatedImpSubj = impSubjEndings.map(ending => {
            return impSubjStem + ending;
        });
    const /**
         * @param {any} ending
         */
        generatedImpSubjWP = impSubjEndingsWP.map(ending => {
            return impSubjStemWP + ending;
        });
    const /**
         * @param {any} ending
         */
        generatedImpSubjWP2 = impSubjEndingsWP.map(ending => {
            return prefixToRemove + impSubjStem + ending;
        });
    compareList(
        impSubj,
        generatedImpSubj,
        `error with imperfect subjunctive endings for ${infinitive}`
    );
    compareList(
        impSubjWP,
        generatedImpSubjWP,
        `error with imperfect subjunctive for ${infinitiveWP}`
    );
    compareList(
        impSubjWP,
        generatedImpSubjWP2,
        `error with {stem + imperfect subjunctive stem + endings} formula for ${infinitiveWP}`
    );

    groupIfStatement += `\t\t\timpSubjStem = stem + "${impSubjStem}";\n`;

    const futSubj = table
        .match(/(?<=Future[\t|\n])(((?!Future)[.|\s|\S])*)(?=Imperative)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const futSubjWP = tableWP
        .match(/(?<=Future[\t|\n])(((?!Future)[.|\s|\S])*)(?=Imperative)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');

    const futSubjStem = futSubj[0];
    const futSubjStemWP = futSubjWP[0];
    if (prefixToRemove + futSubjStem !== futSubjStemWP) {
        throw `error with future subjunctive stem for ${infinitive}/${infinitiveWP}: ${futSubjStem}/${futSubjStemWP}`;
    }
    if (futSubjStem !== infinitive) {
        groupIfStatement += `\t\t\tfutSubjStem = stem + "${futSubjStem}";\n`;
    }
    const futSubjEndAndPersonalInfinitiveEndings = [
        '',
        'es',
        '',
        'mos',
        'des',
        'em'
    ];
    const generatedFutSubj = futSubjEndAndPersonalInfinitiveEndings.map(end => {
        return futSubjStem + end;
    });
    const generatedFutSubjWP = futSubjEndAndPersonalInfinitiveEndings.map(
        end => {
            return futSubjStemWP + end;
        }
    );
    const generatedFutSubjWP2 = futSubjEndAndPersonalInfinitiveEndings.map(
        end => {
            return prefixToRemove + futSubjStem + end;
        }
    );
    compareList(
        futSubj,
        generatedFutSubj,
        `error with future subjunctive for ${infinitive}. Endings don't match.`
    );
    compareList(
        futSubjWP,
        generatedFutSubjWP,
        `error with future subjunctive for ${infinitiveWP}. Endings don't match.`
    );
    compareList(
        futSubjWP,
        generatedFutSubjWP2,
        `error with {stem + future subjunctive stem + endings} formula for ${infinitiveWP}`
    );

    const impAff = table
        .match(
            /(?<=Affirmative[\t|\n])(((?!Affirmative)[.|\s|\S])*?)(?=Negative)/g
        )[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const impAffWP = tableWP
        .match(
            /(?<=Affirmative[\t|\n])(((?!Affirmative)[.|\s|\S])*?)(?=Negative)/g
        )[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .replace(/-/g, 'null')
        .split('\t');

    const generatedImpAffWP = impAff.map(word =>
        word
            .split('/')
            .map(word1 => prefixToRemove + word1)
            .join('/')
    );
    generatedImpAffWP[0] = 'null';
    const impAffValidFormula = compareList(
        impAffWP,
        generatedImpAffWP,
        `imp aff doesnt follow formula for ${infinitiveWP}`
    );
    const impAffWPEndings = impAffWP.map((word, index) =>
        word.substring(word.length - impAff[index].length, word.length)
    );

    const impNeg = table
        .match(/(?<=Negative \(não\))[\s|\S|\n]*/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .replace(/-/g, 'null')
        .split('\t');
    const impNegWP = tableWP
        .match(/(?<=Negative \(não\))[\s|\S|\n]*/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .replace(/-/g, 'null')
        .split('\t');

    const generatedImpNeg = presSubj.slice(0);
    generatedImpNeg[0] = 'null';
    const generatedImpNegWP = presSubjWP.slice(0);
    generatedImpNegWP[0] = 'null';
    const generatedImpNegWP2 = presSubj.slice(0).map(word => {
        return prefixToRemove + word;
    });
    generatedImpNegWP2[0] = 'null';
    compareList(
        impNeg,
        generatedImpNeg,
        `imp neg doesnt follow pattern for ${infinitive}`
    );
    compareList(
        impNegWP,
        generatedImpNegWP,
        `imp neg doesnt follow pattern for ${infinitiveWP}`
    );
    compareList(
        impNegWP,
        generatedImpNegWP2,
        `imp neg doesnt follow formula for ${infinitiveWP}`
    );

    const generatedPersInfEndings = futSubjEndAndPersonalInfinitiveEndings.map(
        end => {
            return infinitive + end;
        }
    );
    const generatedPersInfEndingsWP = futSubjEndAndPersonalInfinitiveEndings.map(
        end => {
            return infinitiveWP + end;
        }
    );
    const generatedPersInfEndingsWP2 = futSubjEndAndPersonalInfinitiveEndings.map(
        end => {
            return prefixToRemove + infinitive + end;
        }
    );
    compareList(
        persInfs,
        generatedPersInfEndings,
        `Pers Inf err ${infinitive}`
    );
    compareList(
        persInfsWP,
        generatedPersInfEndingsWP,
        `Pers Inf err ${infinitiveWP}`
    );
    compareList(
        persInfsWP,
        generatedPersInfEndingsWP2,
        `Pers Inf err formula ${infinitive}`
    );

    groupIfStatement += '\t\t}';

    console.log('\nIntro:\n');
    console.log(groupIfStatement + '\n');

    const mapFunc = (
        stem,
        matches = null,
        verbs = null,
        verbsWPEndings = null
    ) => {
        if (matches !== null) {
            return (word, index) =>
                word === '-' || word === null
                    ? ' null'
                    : ` ${
                          !matches[index]
                              ? `stem.isEmpty() ? "${verbs[index]}" : ${stem} + "${verbsWPEndings[index]}"`
                              : word
                                    .split('/')
                                    .map(word1 => `${stem} + "${word1}"`)
                                    .join(` + "/" + `)
                                    .replace('" + "', '')
                      }`.replace(' + ""', '');
        }
        return word =>
            word === '-' || word === null
                ? ' null'
                : word
                      .split('/')
                      .map(word1 => ` ${stem} + "${word1}"`)
                      .join(` + "/" +`)
                      .replace(' + ""', '')
                      .replace('" + "', '');
    };

    let arrs = [
        presInd.map(
            mapFunc('stem', presIndValidFormula, presInd, presIndWPEndings)
        ),
        impInd.map(
            mapFunc('stem', impIndValidFormula, impInd, impIndWPEndings)
        ),
        pretInd.map(
            mapFunc('stem', pretIndValidFormula, pretInd, pretIndWPEndings)
        ),
        plupInd.map(
            mapFunc('stem', plupIndValidFormula, plupInd, plupIndWPEndings)
        ),
        presSubjEnds.map(mapFunc('presSubjStem')),
        impSubjEndings.map(mapFunc('impSubjStem')),
        futSubjEndAndPersonalInfinitiveEndings.map(mapFunc('futSubjStem')),
        impAff.map(
            mapFunc('stem', impAffValidFormula, impAff, impAffWPEndings)
        ),
        impNegEnds.map(mapFunc('presSubjStem')),
        futSubjEndAndPersonalInfinitiveEndings.map(mapFunc('infinitive'))
    ];

    // for (let arr of arrs) {
    //     console.log(`\t\t\treturn new String[]{${arr} };\n`);
    // }

    let strings = arrs.map(arr => `\t\t\treturn new String[]{${arr} };\n`);

    strings.unshift(groupIfStatement);

    return strings;
}

/**
 * @param {string} table
 */
function processAll(table) {
    const infinitive = table
        .match(/(?<=Impersonal[\t|\n])([\n\s\S]*?)(?=Personal)/g)[0]
        .trim();
    const persInfs = table
        .match(/(?<=Personal[\t|\n])([\n\s\S]*?)(?=Gerund)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');

    const gerund = table
        .match(/(?<=Gerund[\t|\n])([\n\s\S]*?)(?=Past participle)/g)[0]
        .trim();
    const participle = table
        .match(/(?<=Past participle\nMasculine\t)(\S*)/g)[0]
        .trim();

    const generatedGerund =
        infinitive.substring(0, infinitive.length - 1) + 'ndo';
    const ending = infinitive.substring(
        infinitive.length - 2,
        infinitive.length
    );
    const generatedPart =
        infinitive.substring(0, infinitive.length - 2) +
        (ending === 'ar' ? 'ado' : 'ido');
    let introString = '';

    if (generatedGerund !== gerund || generatedPart !== participle) {
        introString += `\t\t\tgerund = "${gerund}";\n\t\t\tparticiple = "${participle}";\n`;
    }

    const preses = table.match(/(?<=Present[\t|\n])([\n\s\S]*?)(?=Imperfect)/g);

    const presInd = preses[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');

    const impInd = table
        .match(/(?<=Imperfect[\t|\n])([\n\s\S]*?)(?=Preterite)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const pretInd = table
        .match(
            /(?<=Preterite[\t|\n])(((?!Preterite)[.|\s|\S])*)(?=Pluperfect)/g
        )[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const plupInd = table
        .match(
            /(?<=Pluperfect[\t|\n])(((?!Pluperfect)[.|\s|\S])*?)(?=Future)/g
        )[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const futInd = table
        .match(/(?<=Future[\t|\n])([\n\s\S]*?)(?=Conditional)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const cond = table
        .match(
            /(?<=Conditional[\t|\n])(((?!Conditional)[.|\s|\S])*)(?=Subjunctive)/g
        )[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');

    const futIndStem = futInd[0].substring(0, futInd[0].length - 2);
    const conIndStem = cond[0].substring(0, cond[0].length - 2);
    if (futIndStem !== conIndStem) {
        console.log('ANALYZE FUT AND COND IND - missing futAndCondStem');
    } else if (futIndStem !== infinitive) {
        introString += `\t\t\tfutAndCondStem = "${futIndStem}";\n`;
    }
    const futIndEndings = ['ei', 'ás', 'á', 'emos', 'eis', 'ão'];
    const condEndings = ['ia', 'ias', 'ia', 'íamos', 'íeis', 'iam'];
    const generatedfutInd = futIndEndings.map(end => {
        return futIndStem + end;
    });
    const generatedCond = condEndings.map(end => {
        return conIndStem + end;
    });
    compareList(futInd, generatedfutInd, 'ERROR WITH FUT IND');
    compareList(cond, generatedCond, 'ERROR WITH COND');

    const presSubj = preses[1].trim().split('\t');
    const presSubjStem = presSubj[0].substring(0, presSubj[0].length - 1);
    const presSubjEnd = presSubj[0].charAt(presSubj[0].length - 1);
    if (ending === 'ar' && presSubjEnd !== 'e') {
        introString +=
            '\t\t\tisArEndings = false;\n\t\t\tisArEndingsSet = true;\n';
    } else if (ending !== 'ar' && presSubjEnd !== 'a') {
        introString +=
            '\t\t\tisArEndings = true;\n\t\t\tisArEndingsSet = true;\n';
    }
    introString += `\t\t\tpresSubjStem = "${presSubjStem}";\n`;

    const subjEndAr = ['e', 'es', 'e', 'emos', 'eis', 'em'];
    const subjEndNotAr = ['a', 'as', 'a', 'amos', 'ais', 'am'];
    const selectedPresSubjEnds = ending === 'ar' ? subjEndAr : subjEndNotAr;
    const generatedPresSubj = selectedPresSubjEnds.map(end => {
        return presSubjStem + end;
    });
    compareList(presSubj, generatedPresSubj, 'ERROR WITH PRES SUBJ');

    const impSubj = table
        .match(/(?<=Imperfect[\t|\n])([\n\s\S]*?)(?=Future)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const impSubjStem = impSubj[0].substring(0, impSubj[0].length - 4);
    const /**
         * @param {string} word
         */
        impSubjEndings = impSubj.map(word => {
            return word.substring(impSubjStem.length, word.length);
        });
    const /**
         * @param {any} ending
         */
        generatedImpSubj = impSubjEndings.map(ending => {
            return impSubjStem + ending;
        });
    compareList(impSubj, generatedImpSubj, 'ERROR WITH IMP SUBJ');
    introString += `\t\t\timpSubjStem = "${impSubjStem}";\n`;

    const futSubj = table
        .match(/(?<=Future[\t|\n])(((?!Future)[.|\s|\S])*)(?=Imperative)/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    if (futSubj[0] !== infinitive) {
        introString += `\t\t\tfutSubjStem = "${futSubj[0]}";\n`;
    }
    const futSubjEndAndPersonalInfinitiveEndings = [
        '',
        'es',
        '',
        'mos',
        'des',
        'em'
    ];
    const generatedFutSubj = futSubjEndAndPersonalInfinitiveEndings.map(end => {
        return futSubj[0] + end;
    });
    compareList(futSubj, generatedFutSubj, 'ERROR WITH FUTURE SUBJ');

    const impAff = table
        .match(
            /(?<=Affirmative[\t|\n])(((?!Affirmative)[.|\s|\S])*?)(?=Negative)/g
        )[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');
    const impNeg = table
        .match(/(?<=Negative \(não\))[\s|\S|\n]*/g)[0]
        .trim()
        .replace(/[\s]*\n[\s]*/g, '/')
        .split('\t');

    impNeg[0] = 'null';
    const generatedImpNeg = presSubj.slice(0);
    generatedImpNeg[0] = 'null';
    compareList(impNeg, generatedImpNeg, 'IMP NEG ERR');

    const generatedPersInfEndings = futSubjEndAndPersonalInfinitiveEndings.map(
        end => {
            return infinitive + end;
        }
    );
    compareList(persInfs, generatedPersInfEndings, 'pers inf ERR');

    console.log('\nIntro:\n');
    console.log(introString + '\n');

    console.log('\nconjugatePresInd: \n');
    let arr = presInd;
    /**
     * @param {any} word
     */
    console.log(
        `\t\t\treturn new String[]{ "${arr[0]}", "${arr[1]}", "${arr[2]}", "${arr[3]}", "${arr[4]}", "${arr[5]}" };\n`.replace(
            /\s\+\s""/g,
            ''
        )
    );

    console.log('\nconjugateImpInd: \n');
    arr = impInd;
    /**
     * @param {any} word
     */
    console.log(
        `\t\t\treturn new String[]{ "${arr[0]}", "${arr[1]}", "${arr[2]}", "${arr[3]}", "${arr[4]}", "${arr[5]}" };\n`.replace(
            /\s\+\s""/g,
            ''
        )
    );

    console.log('\nconjugatePretInd: \n');
    arr = pretInd;
    /**
     * @param {any} word
     */
    console.log(
        `\t\t\treturn new String[]{ "${arr[0]}", "${arr[1]}", "${arr[2]}", "${arr[3]}", "${arr[4]}", "${arr[5]}" };\n`.replace(
            /\s\+\s""/g,
            ''
        )
    );

    console.log('\nconjugateSimpPlupInd: \n');
    arr = plupInd;
    /**
     * @param {any} word
     */
    console.log(
        `\t\t\treturn new String[]{ "${arr[0]}", "${arr[1]}", "${arr[2]}", "${arr[3]}", "${arr[4]}", "${arr[5]}" };\n`.replace(
            /\s\+\s""/g,
            ''
        )
    );

    console.log('\nconjugateImpSubj: \n');
    arr = impSubjEndings;
    /**
     * @param {any} word
     */
    console.log(
        `\t\t\treturn new String[]{ impSubjStem + "${arr[0]}", impSubjStem + "${arr[1]}", impSubjStem + "${arr[2]}", impSubjStem + "${arr[3]}", impSubjStem + "${arr[4]}", impSubjStem +"${arr[5]}" };\n`.replace(
            /\s\+\s""/g,
            ''
        )
    );

    console.log('\nconjugateFutSubj: \n');
    arr = futSubjEndAndPersonalInfinitiveEndings;
    /**
     * @param {any} word
     */
    console.log(
        `\t\t\treturn new String[]{ futSubjStem + "${arr[0]}", futSubjStem + "${arr[1]}", futSubjStem + "${arr[2]}", futSubjStem + "${arr[3]}", futSubjStem + "${arr[4]}", futSubjStem +"${arr[5]}" };\n`.replace(
            /\s\+\s""/g,
            ''
        )
    );

    console.log('\nconjugateImpAff: \n');
    arr = impAff;
    /**
     * @param {any} word
     */
    console.log(
        `\t\t\treturn new String[]{ null, "${arr[1]}", "${arr[2]}", "${arr[3]}", "${arr[4]}", "${arr[5]}" };\n`.replace(
            /\s\+\s""/g,
            ''
        )
    );

    console.log('\nconjugatePersInf: \n');
    arr = futSubjEndAndPersonalInfinitiveEndings;
    /**
     * @param {any} word
     */
    console.log(
        `\t\t\treturn new String[]{ infinitive + "${arr[0]}", infinitive + "${arr[1]}", infinitive + "${arr[2]}", infinitive + "${arr[3]}", infinitive + "${arr[4]}", infinitive + "${arr[5]}" };\n`.replace(
            /\s\+\s""/g,
            ''
        )
    );
}

/**
 * @param {any[]} tenseFromTable
 * @param {string | any[]} generatedTense
 * @param {string} error
 */
function compareList(tenseFromTable, generatedTense, error) {
    let same = true;
    if (tenseFromTable.length !== generatedTense.length) {
        throw `error, lists lengths dont match: \n${tenseFromTable}\n${generatedTense}\n`;
    }
    const matches = [];
    for (let i = 0; i < generatedTense.length; i++) {
        let match = generatedTense[i] === tenseFromTable[i];
        matches.push(match);
        if (!match) {
            same = false;
        }
    }
    if (!same) {
        console.log(
            `${error}'\n'${tenseFromTable}'\n'${generatedTense}\n${matches}`
        );
    }
    return matches;
}

// ODIR_ATIR, OLIR

const group = `AIR, OLIR, ODIR_ATIR, ABRIR, TER, VIR, FAZER,
IAR, POR, DIZER, CABER, SEGUIR, CRER, COBRIR, DAR, ESTAR, HAVER,

GEAR, NEVAR

IR, OUVIR, PERDER, PODER, PROVER, SABER,
SER, TOSSIR, TRAZER, ENGOLIR, FUGIR, EDIR, DIVERTIR,
SERVIR, SAUDAR, REUNIR, REMIR, 
DORMIR, PÔR,

LER, QUERER, VER, RIR, VALER, GREDIR, EDIR, ENTIR, ELIR,
REG_AR, REG_ER, REG_IR, EAR, OIBIR, PREVENIR, ERIR,
OER, OIAR, VESTIR, TUIR, BUIR, STRUIR, ERZIR`;

function getAllVerbs() {
    let dualTables = [
        [
            'TER',
            'ter|abster|conter|suster|ater|deter|entreter|manter|obter|reter',
            `Infinitive
Impersonal	ter
Personal	ter	teres	ter	termos	terdes	terem
Gerund
tendo
Past participle
Masculine	tido	tidos
Feminine	tida	tidas
Indicative
Present	tenho	tens	tem	temos	tendes	têm
Imperfect	tinha	tinhas	tinha	tínhamos	tínheis	tinham
Preterite	tive	tiveste	teve	tivemos	tivestes	tiveram
Pluperfect	tivera	tiveras	tivera	tivéramos	tivéreis	tiveram
Future	terei	terás	terá	teremos	tereis	terão
Conditional
teria	terias	teria	teríamos	teríeis	teriam
Subjunctive
Present	tenha	tenhas	tenha	tenhamos	tenhais	tenham
Imperfect	tivesse	tivesses	tivesse	tivéssemos	tivésseis	tivessem
Future	tiver	tiveres	tiver	tivermos	tiverdes	tiverem
Imperative
Affirmative	-	tem	tenha	tenhamos	tende	tenham
Negative (não)	-	tenhas	tenha	tenhamos	tenhais	tenham`,
            `Infinitive
Impersonal	abster
Personal	abster	absteres	abster	abstermos	absterdes	absterem
Gerund
abstendo
Past participle
Masculine	abstido	abstidos
Feminine	abstida	abstidas
Indicative
Present	abstenho	absténs	abstém	abstemos	abstendes	abstêm
Imperfect	abstinha	abstinhas	abstinha	abstínhamos	abstínheis	abstinham
Preterite	abstive	abstiveste	absteve	abstivemos	abstivestes	abstiveram
Pluperfect	abstivera	abstiveras	abstivera	abstivéramos	abstivéreis	abstiveram
Future	absterei	absterás	absterá	absteremos	abstereis	absterão
Conditional
absteria	absterias	absteria	absteríamos	absteríeis	absteriam
Subjunctive
Present	abstenha	abstenhas	abstenha	abstenhamos	abstenhais	abstenham
Imperfect	abstivesse	abstivesses	abstivesse	abstivéssemos	abstivésseis	abstivessem
Future	abstiver	abstiveres	abstiver	abstivermos	abstiverdes	abstiverem
Imperative
Affirmative	-	abstém	abstenha	abstenhamos	abstende	abstenham
Negative (não)	-	abstenhas	abstenha	abstenhamos	abstenhais	abstenham`
        ],
        [
            'FAZER',
            null,
            `Infinitive
Impersonal	fazer
Personal	fazer	fazeres	fazer	fazermos	fazerdes	fazerem
Gerund
fazendo
Past participle
Masculine	feito	feitos
Feminine	feita	feitas
Indicative
Present	faço	fazes	faz	fazemos	fazeis	fazem
Imperfect	fazia	fazias	fazia	fazíamos	fazíeis	faziam
Preterite	fiz	fizeste	fez	fizemos	fizestes	fizeram
Pluperfect	fizera	fizeras	fizera	fizéramos	fizéreis	fizeram
Future	farei	farás	fará	faremos	fareis	farão
Conditional
faria	farias	faria	faríamos	faríeis	fariam
Subjunctive
Present	faça	faças	faça	façamos	façais	façam
Imperfect	fizesse	fizesses	fizesse	fizéssemos	fizésseis	fizessem
Future	fizer	fizeres	fizer	fizermos	fizerdes	fizerem
Imperative
Affirmative	-	faz
faze	faça	façamos	fazei	façam
Negative (não)	-	faças	faça	façamos	façais	façam`,
            `Infinitive
Impersonal	liquefazer
Personal	liquefazer	liquefazeres	liquefazer	liquefazermos	liquefazerdes	liquefazerem
Gerund
liquefazendo
Past participle
Masculine	liquefeito	liquefeitos
Feminine	liquefeita	liquefeitas
Indicative
Present	liquefaço	liquefazes	liquefaz	liquefazemos	liquefazeis	liquefazem
Imperfect	liquefazia	liquefazias	liquefazia	liquefazíamos	liquefazíeis	liquefaziam
Preterite	liquefiz	liquefizeste	liquefez	liquefizemos	liquefizestes	liquefizeram
Pluperfect	liquefizera	liquefizeras	liquefizera	liquefizéramos	liquefizéreis	liquefizeram
Future	liquefarei	liquefarás	liquefará	liquefaremos	liquefareis	liquefarão
Conditional
liquefaria	liquefarias	liquefaria	liquefaríamos	liquefaríeis	liquefariam
Subjunctive
Present	liquefaça	liquefaças	liquefaça	liquefaçamos	liquefaçais	liquefaçam
Imperfect	liquefizesse	liquefizesses	liquefizesse	liquefizéssemos	liquefizésseis	liquefizessem
Future	liquefizer	liquefizeres	liquefizer	liquefizermos	liquefizerdes	liquefizerem
Imperative
Affirmative	-	liquefaz
liquefaze	liquefaça	liquefaçamos	liquefazei	liquefaçam
Negative (não)	-	liquefaças	liquefaça	liquefaçamos	liquefaçais	liquefaçam`
        ]
    ];

    let verbGroups = [];

    for (let tableGroups of dualTables) {
        verbGroups.push(
            getVerbGroup(
                tableGroups[0],
                tableGroups[1],
                tableGroups[2],
                tableGroups[3]
            )
        );
    }

    const verbsIntros = `\t\t${verbGroups
        .map(verbs => verbs[0])
        .join(' else ')}`;
    console.log(verbsIntros);

    // intro
    // presInd
    // impInd
    // pretInd
    // plupInd
    // presSubj
    // impSubj
    // futSubj
    // impAff
    // impNeg
    // personalInfinitive
}

getAllVerbs();
