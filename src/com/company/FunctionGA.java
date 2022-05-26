package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.company.Main.*;
import static com.company.OtherFunctions.*;

public class FunctionGA {
    public static int id = 1;
    public static int populationSize = 10;

    public static List<Chromosome> initPopulation(int max) {

        List<Chromosome> chromosomeList = new ArrayList<>();
        do {
            Chromosome chromosome = new Chromosome();
            chromosome.setTeamList(genChromosome(taskList, agentContractorList, max));
            chromosomeList.add(chromosome);
        } while (id < (populationSize + 1));
        return chromosomeList;
    }

    public static Chromosome[] select(List<Chromosome> chromosomeList) {
        Chromosome[] chromosomes = new Chromosome[2];
        Collections.sort(chromosomeList);
        result.add(chromosomeList.get(0));
        chromosomes[0] = findParentChromosome(chromosomeList);
        boolean check = false;
        do {
            chromosomes[1] = findParentChromosome(chromosomeList);
            if (!chromosomes[0].equals(chromosomes[1])) {
                check = true;
            }
        } while (!check);

        return chromosomes;
    }

    public static List<Chromosome> crossOver(Chromosome[] chromosomes) {
        List<Chromosome> chromosomeList = new ArrayList<>();
        int ProbabilityCrossover = ThreadLocalRandom.current().nextInt(1, 11);
        if (ProbabilityCrossover < 9) {
            HashMap<Agent, Task> offSpringHm1 = new HashMap<>(chromosomes[0].getChartChromosome());
            HashMap<Agent, Task> offSpringHm2 = new HashMap<>(chromosomes[1].getChartChromosome());

            int numberRandom = ThreadLocalRandom.current().nextInt(1, agentContractorList.size());

            // Crossover two offsprings by Hashmap
            for (int i = agentContractorList.size() - 1; i >= agentContractorList.size() - numberRandom; i--) {
                Agent agent = agentContractorList.get(i);
                Task temp = offSpringHm1.get(agent);
                offSpringHm1.put(agent, offSpringHm2.get(agent));
                offSpringHm2.put(agent, temp);
            }

            Chromosome offSpring1 = null;
            Chromosome offSpring2 = null;

            // Validate the offspring
            if (checkInvalidChromosome(offSpringHm1, 3)) {
                offSpring1 = new Chromosome();
                offSpring1.setChromosomeInfo(offSpringHm1);
            }
            if (checkInvalidChromosome(offSpringHm2, 3)) {
                offSpring2 = new Chromosome();
                offSpring2.setChromosomeInfo(offSpringHm2);
            }

            if (offSpring1 != null) {
                chromosomeList.add(offSpring1);
            }
            if (offSpring2 != null) {
                chromosomeList.add(offSpring2);
            }
            if (chromosomeList.size() == 0) {
                chromosomeList.add(chromosomes[0]);
                chromosomeList.add(chromosomes[1]);
            }

            return chromosomeList;
        }
        return chromosomeList;
    }


    public static List<Chromosome> mutation(List<Chromosome> chromosomeList) {
        int ProbabilityMutation = ThreadLocalRandom.current().nextInt(1, 11);
        if (ProbabilityMutation < 3) {
            int random1 = 0;
            if (chromosomeList.size() != 1) {
                random1 = ThreadLocalRandom.current().nextInt(0, chromosomeList.size());
            }
            Chromosome chromosome = chromosomeList.get(random1);
            HashMap<Agent, Task> chart = chromosome.getChartChromosome();

            int random2 = ThreadLocalRandom.current().nextInt(0, agentContractorList.size());
            Agent key = agentContractorList.get(random2);
            if (chart.get(key) == null) {
                int random3 = ThreadLocalRandom.current().nextInt(0, taskList.size());
                Task task = taskList.get(random3);
                chart.put(key, task);
            } else {
                List<Task> taskListCopy = new ArrayList<>(taskList);
                taskListCopy.remove(chart.get(key));
                int random4 = ThreadLocalRandom.current().nextInt(-1, taskListCopy.size());
                if (random4 == -1) {
                    chart.put(key, null);
                } else {
                    chart.put(key, taskListCopy.get(random4));
                }
            }

            if (!checkInvalidChromosome(chart, 3)) {
                chromosomeList.remove(chromosome);
            } else {
                chromosome.setChromosomeInfo(chart);
            }
            return chromosomeList;
        }
        return chromosomeList;
    }
}