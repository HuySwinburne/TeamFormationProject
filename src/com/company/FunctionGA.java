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
            chromosome.setId(id);
            chromosomeList.add(chromosome);
            id++;
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

//        System.out.println(chromosomes[0].getChartChromosome());
//        System.out.println(chromosomes[1].getChartChromosome());
        return chromosomes;
    }

    public static Chromosome findParentChromosome(List<Chromosome> chromosomeList) {
        int randomIndex1 = ThreadLocalRandom.current().nextInt(1, chromosomeList.size() - 1);
        int randomIndex2 = ThreadLocalRandom.current().nextInt(1, chromosomeList.size() - 1);
        return chromosomeList.get(randomIndex1).getFitness() > chromosomeList.get(randomIndex2).getFitness()
                ? chromosomeList.get(randomIndex1) : chromosomeList.get(randomIndex2);
    }

    public static List<Chromosome> crossOver(Chromosome[] chromosomes) {
        HashMap<Agent, Task> offSpringHm1 = new HashMap<>(chromosomes[0].getChartChromosome());
        HashMap<Agent, Task> offSpringHm2 = new HashMap<>(chromosomes[1].getChartChromosome());

        int numberRandom = ThreadLocalRandom.current().nextInt(1, agentContractorList.size());
//        System.out.println(numberRandom);

        // Crossover two offsprings by Hashmap
        for (int i = agentContractorList.size() - 1; i >= agentContractorList.size() - numberRandom; i--) {
            Agent agent = agentContractorList.get(i);
            Task temp = offSpringHm1.get(agent);
            offSpringHm1.put(agent, offSpringHm2.get(agent));
            offSpringHm2.put(agent, temp);
        }
//        System.out.println(offSpringHm1);
//        System.out.println(offSpringHm2 + "\n");

        Chromosome offSpring1 = null;
        Chromosome offSpring2 = null;

        // Validate the offspring
//        System.out.println("Crossover result:\n");
        if (checkInvalidChromosome(offSpringHm1, 3)) {
            offSpring1 = new Chromosome(offSpringHm1);
//            System.out.println(offSpring1);
//            System.out.println(offSpring1.getChartChromosome() + "\n");
        }
//        else{
//            System.out.println("Chromosome is not valid\n");
//        }
        if (checkInvalidChromosome(offSpringHm2, 3)) {
            offSpring2 = new Chromosome(offSpringHm2);
//            System.out.println(offSpring2);
//            System.out.println(offSpring2.getChartChromosome() + "\n");
        }
//        else{
//            System.out.println("Chromosome is not valid\n");
//        }

        List<Chromosome> chromosomeList = new ArrayList<>();
        if (offSpring1 != null) {
            chromosomeList.add(offSpring1);
        }
        if (offSpring2 != null) {
            chromosomeList.add(offSpring2);
        }

        return chromosomeList;
    }
}