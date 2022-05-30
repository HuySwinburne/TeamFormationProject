package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.company.Main.*;
import static com.company.OtherFunctions.*;
import static com.company.GAConfig.*;

public class FunctionGA {

    public static int id = 1;
    public static List<Chromosome> initPopulation() {

        List<Chromosome> chromosomeList = new ArrayList<>();
        do {
            Chromosome chromosome = new Chromosome();
            chromosome.setTeamList(genChromosome(taskList, agentContractorList));
            chromosomeList.add(chromosome);
        } while (id < populationSize + 1);
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
        int Probability = ThreadLocalRandom.current().nextInt(0, 10);
        if (Probability < probabilityCrossover) {
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
            if (checkInvalidChromosome(offSpringHm1, numberOfAgents)) {
                offSpring1 = new Chromosome();
                offSpring1.setChromosomeInfo(offSpringHm1);
            }
            if (checkInvalidChromosome(offSpringHm2, numberOfAgents)) {
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
        chromosomeList.add(chromosomes[0]);
        chromosomeList.add(chromosomes[1]);
        return chromosomeList;
    }

    public static List<Chromosome> mutation(List<Chromosome> chromosomeList, List<Chromosome> population) {
        int Probability = ThreadLocalRandom.current().nextInt(0, 10);
        if (Probability < probabilityMutation) {
            int random1 = 0;
            if (chromosomeList.size() != 1) {
                random1 = ThreadLocalRandom.current().nextInt(0, chromosomeList.size());
            }
            Chromosome chromosome = chromosomeList.get(random1);
            HashMap<Agent, Task> chart = new HashMap<>(chromosome.getChartChromosome());

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

            List<Chromosome> lstMutation = new ArrayList<>();
            if (checkInvalidChromosome(chart, numberOfAgents)) {
                if (new HashSet<>(population).containsAll(chromosomeList)) {
                    Chromosome result = new Chromosome();
                    result.setChromosomeInfo(chart);
                    lstMutation.add(result);
                } else {
                    chromosome.setChromosomeInfo(chart);
                    lstMutation.addAll(chromosomeList);
                }
            } else if (!new HashSet<>(population).containsAll(chromosomeList)) {
                chromosomeList.remove(chromosome);
                lstMutation.addAll(chromosomeList);
            }
            return lstMutation;
        }
        return chromosomeList;
    }

    public static Gen generateGen(int id, List<Chromosome> chromosomeList){
        List<Chromosome> chromosomeListNew = new ArrayList<>(chromosomeList);
        do {
            Chromosome[] selectedChromosome = select(chromosomeList);
            List<Chromosome> chromosomeListCross = crossOver(selectedChromosome);
            List<Chromosome> mutationList = mutation(chromosomeListCross, chromosomeList);
            if (mutationList.size() > 0) {
                chromosomeListNew.addAll(mutationList);
            }
        } while (chromosomeListNew.size() < populationSize*2);

        Collections.sort(chromosomeListNew);
        if (chromosomeListNew.size() > populationSize) {
            chromosomeListNew.subList(populationSize, chromosomeListNew.size()).clear();
        }
        return new Gen(id,chromosomeListNew);
    }

    public static List<Gen> generateGens(){
        int i = 0;
        List<Chromosome> chromosomeList = initPopulation();
        Gen gen = new Gen(i, chromosomeList);
        List<Gen> genList = new ArrayList<>();
        genList.add(gen);
        do {
            List<Chromosome> chromosomes = genList.get(i++).getChromosomeList();
            Gen genNew = generateGen(i, chromosomes);
            genList.add(genNew);
        } while (i < generationLimit);

        return genList;
    }

    public static Chromosome printResult(List<Chromosome> chromosomeResult) {
        Chromosome chromosome = null;
        boolean flag = false;
        for (Chromosome chromosome1 : chromosomeResult) {
            int count = 0;
            for (Chromosome other : chromosomeResult) {
                if (other.getFitness() == chromosome1.getFitness()) {
                    count++;
                } else if (count > 0) {
                    break;
                }
            }
            if (count >= convergenceCondition) {
                chromosome = chromosome1;
                flag = true;
                break;
            }
        }
        if (!flag) {
            Collections.sort(chromosomeResult);
            chromosome = chromosomeResult.get(0);
        }
        System.out.println(chromosome);
        return chromosome;
    }
}