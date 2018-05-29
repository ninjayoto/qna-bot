package com.infosupport.demos.qnabot.training;

import org.deeplearning4j.api.storage.StatsStorage;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Classifies questions to possible answers.
 */
public final class QuestionClassifier {
    private final MultiLayerNetwork network;
    private final QuestionVectorizer vectorizer;
    private final StatsStorage statsStorage;
    private final Map<Integer, String> answers;

    /**
     * Initializes a new instance of {@link QuestionClassifier}
     *
     * @param network      Neural network to use for classification
     * @param statsStorage Storage to use for statistics during training
     */
    public QuestionClassifier(MultiLayerNetwork network,
                              QuestionVectorizer vectorizer,
                              StatsStorage statsStorage,
                              Map<Integer, String> answers) {
        this.network = network;
        this.vectorizer = vectorizer;
        this.statsStorage = statsStorage;
        this.answers = answers;
    }

    /**
     * Trains the classifier on questions in the specified input file
     *
     * @param inputFile Input file to use
     */
    public void fit(File inputFile) throws IOException, InterruptedException {
        QuestionDataSource dataSource=  new QuestionDataSource(
                inputFile, vectorizer, 32, answers.size());

        for(int epoch = 0; epoch < 50; epoch++) {
            Batch nextBatch = dataSource.next();

            network.fit(nextBatch.getFeatures(), nextBatch.getLabels());
        }
    }

    /**
     * Makes a prediction of the possible answer based on the given input
     *
     * @param text The question the user asked
     * @return The highest ranking answer
     */
    public String predict(String text) {
        return null;
    }

    /**
     * Saves the trained neural network to disk
     *
     * @param outputFile Output file to store the model to
     */
    public void save(File outputFile) {

    }

    /**
     * Gets the storage for the model statistics
     *
     * @return The instance of the model statistics storage
     */
    public StatsStorage getStatsStorage() {
        return statsStorage;
    }
}