package Servise;

import Repo.DonorRepo;
import pojo.Data;
import pojo.Result;
import pojo.donorData;
import pojo.samanData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class optest {
    public List<Result> getResult() {
        Scanner sc = new Scanner(System.in);

        DonorRepo donorRepo = new DonorRepo();

        List<donorData> donor = donorRepo.getDD();
        List<samanData> saman = donorRepo.getSD();


        List<Result> result = new ArrayList<>();
        for (donorData i : donor) {
            result.add(new Result(i.getName(), i.getGau(), i.getAnn()));
        }

        //for prize per kg
        List<Integer> samantest=new ArrayList<>();
        int transport = donorRepo.getTransport();
        double TotalKg = 0;

        for (samanData i : saman) {
            samantest.add(i.getKg());
            TotalKg += i.getKg();
        }


        int sumofdonation=0;
        int sumofsaman=transport;
        for(donorData i : donor)
        {
            sumofdonation+=i.getAnn()+i.getGau();
        }
        for (samanData i : saman)
        {
            sumofsaman+=i.getBill();
        }
        System.out.println("\nsum of donation : "+sumofdonation);
        System.out.println("sum of lidhelo saman : "+sumofsaman+"\n");
        List<Double> prizePerKg = new ArrayList<>();

        double transportPerKg = transport / TotalKg;

        for (int i = 0; i <saman.size(); i++) {
            double d = (double) saman.get(i).getBill() / saman.get(i).getKg();
            d += transportPerKg;

            //point pachi 3 number sudhi round
            d = Math.round(d * 1000);
            d = d / 1000;

            prizePerKg.add(d);
        }

        //selected vastu donation mate
        for (;;)
        {
            int cycle=0;
            int cycle1=0;

            System.out.print("Enter name of donor (0 if none): ");
            String name = sc.nextLine();
            if(name.equals("0")) break;
            int tempcheck=0;
            for(donorData i : donor)
            {
                if(i.getName().equals(name)) tempcheck=1;
            }
            if(tempcheck==0) {
                System.out.println("name does not match try again");
                continue;
            }

            System.out.print("Enter name of item : ");
            String item = sc.nextLine();

            int tempcheck1=0;
            for(samanData i : saman)
            {
                if(i.getName().equals(item)) break;
                tempcheck1++;
            }
            if(tempcheck1>=saman.size()) {
                System.out.println("name does not match try again");
                continue;
            }


            int check = 0;
            int sPri=0;
            int sKg=0;
            System.out.print("Enter how many kg or how much money : ");
            String chstr = sc.nextLine();
            if(chstr.contains("kg"))
            {
                check=1;
                sKg = Integer.parseInt(chstr.substring(0,chstr.length()-2));
            }
            else
            {
                sPri = Integer.parseInt(chstr);
            }

            for (int i = 0; i < result.size()&&cycle==0; i++) {
                if (result.get(i).getName().equalsIgnoreCase(name)) {
                    cycle=1;
                    for (int j = 0; j < saman.size()&&cycle1==0; j++) {
                        if (saman.get(j).getName().equalsIgnoreCase(item)) {
                            cycle1=1;

                            if (check == 1) sPri=(int)(sKg*prizePerKg.get(j));
                            else
                            {
                                sKg=(int)(sPri/prizePerKg.get(j));
                                sPri=(int)(sKg*prizePerKg.get(j));
                            }

                            if((donor.get(i).getGau()<sPri)&&tempcheck1<3)
                            {
                                System.out.println("donation is less than wanted donation");
                                break;
                            }

                            if((donor.get(i).getAnn()<sPri)&&tempcheck1>=3)
                            {
                                System.out.println("donation is less than wanted donation");
                                break;
                            }

                            if(j<3) {
                                if(donor.get(i).getGau()<sPri)
                                {
                                    donor.get(i).setAnn(donor.get(i).getAnn()-(sPri-donor.get(i).getGau()));
                                    result.get(i).setGau(result.get(i).getGau()+sPri);
                                    result.get(i).setAnn(result.get(i).getAnn()-sPri);
                                    donor.get(i).setGau(0);

                                }
                                else {
                                    donor.get(i).setGau(donor.get(i).getGau() - sPri);
                                }
                                result.get(i).getDataG().add(new Data(item, sKg));
                            }
                            else {
                                result.get(i).getDataA().add(new Data(item, sKg));
                                donor.get(i).setAnn(donor.get(i).getAnn() -sPri);
                            }
                            saman.get(j).setKg(saman.get(j).getKg() - sKg);
                        }
                    }
                }
            }
            System.out.println();
        }

        int gauDonor=0;
        int gauSaman = donorRepo.getTotalGauSaman();
        for(;gauDonor<donor.size();gauDonor++)
        {
            if(donor.get(gauDonor).getGau()==0) break;
        }
        System.out.println("\n\n\n gaudonor = "+gauDonor+"\n\n\n");



        List<Double> ddg = new ArrayList<>();
        List<Double> dda = new ArrayList<>();

            //
                int totalgau = 0;
                for (int i = 0; i < gauSaman; i++) {
                    totalgau += saman.get(i).getKg() * prizePerKg.get(i);
                }

                for (donorData s : donor) {
                    ddg.add((double) s.getGau());
                    //System.out.println("before ddg : " + s.getGau());
                }



                int j = 0, k = gauSaman, tempKg, tempKgA;
                double tempMonyGau, tempMonyAnn;
                tempKg = saman.get(0).getKg();
                tempKgA = saman.get(gauSaman).getKg();
                int v = 0, y = 0;

                List<Integer> total = new ArrayList<>();
                for (int i = 0; i < saman.size(); i++) {
                    total.add(0);
                }
            //

        for(int i=0;i<gauDonor;i++)
        {
            double temp = 0;
            int devanaKg;
            int tmg = 0, tma = 0;

            if (i > 0) {
                tempMonyGau = donor.get(i).getGau() + ddg.get(i - 1);
                ddg.set(i-1,0.0);

            } else {
                tempMonyGau = donor.get(i).getGau();
            }

            while (tempMonyGau > prizePerKg.get(j) && j < gauSaman) {
                temp = Math.round(tempMonyGau / prizePerKg.get(j));
                if (temp > tempKg) {

                    devanaKg = tempKg;
                    tmg += prizePerKg.get(j) * devanaKg;

                    total.set(j, total.get(j) + devanaKg);

                    ddg.set(i, ddg.get(i) - (devanaKg * prizePerKg.get(j)));
                    //System.out.print(saman[j]+":"+devanaKg+"kg ");
                    result.get(i).getDataG().add(new Data(saman.get(j).getName(), devanaKg));
                    System.out.println(result.get(i).toString());

                    tempKg -= devanaKg;
                    tempMonyGau -= (prizePerKg.get(j) * devanaKg);
                    totalgau -= prizePerKg.get(j) * devanaKg;
                    j++;
                    tempKg = saman.get(j).getKg();

                }
                else {

                    devanaKg = (int) temp;
                    total.set(j, total.get(j) + devanaKg);
                    tmg += prizePerKg.get(j) * devanaKg;

                    ddg.set(i, ddg.get(i) - (devanaKg * prizePerKg.get(j)));

                    result.get(i).getDataG().add(new Data(saman.get(j).getName(), devanaKg));
                    System.out.println(result.get(i).toString());
                    tempKg -= devanaKg;
                    tempMonyGau -= prizePerKg.get(j) * devanaKg;
                    totalgau -= prizePerKg.get(j) * devanaKg;

                }
            }
            if (i < donor.size() - 1) {
                ddg.set(i + 1,( ddg.get(i) + ddg.get(i + 1)));
            }
        }

        for(Result i : result)
        {
            System.out.println(i.toString());
        }
        System.out.println("\n\n\n\n");

        int nGauDonor=0;
        for(int i=gauDonor;i<donor.size();i++)
        {
            System.out.println("total gau ="+totalgau);
            int buffermargin=4000;
            if (donor.get(i).getGau() == 0 && donor.get(i).getAnn() >= buffermargin && totalgau >= buffermargin) {

                donor.get(i).setGau(donor.get(i).getGau() + buffermargin);
                result.get(i).setGau(result.get(i).getGau()+buffermargin);

                result.get(i).setAnn(result.get(i).getAnn() - buffermargin);
                donor.get(i).setAnn(donor.get(i).getAnn() - buffermargin);

                totalgau-=buffermargin;
                double x = ddg.get(i) + buffermargin;
                ddg.set(i, x);
                //System.out.println(" test : " + ddg.get(i));
//                x = dda.get(i) - buffermargin;
//                dda.set(i, x);
                //System.out.println("test : " + dda.get(i));
            }
            else if (donor.get(i).getGau() == 0 && donor.get(i).getAnn() >= totalgau && (totalgau > 0)) {
                result.get(i).setGau(result.get(i).getGau() + totalgau);
                donor.get(i).setGau(donor.get(i).getGau() + totalgau);

                result.get(i).setAnn(result.get(i).getAnn() - totalgau);
                donor.get(i).setAnn(donor.get(i).getAnn() - totalgau);

                double x = ddg.get(i) + totalgau;
                ddg.set(i, x);
                //System.out.println(" test : " + ddg.get(i));
//                x = dda.get(i) - totalgau;
//                dda.set(i, x);
                //System.out.println("test : " + dda.get(i));
                totalgau = 0;
                nGauDonor=i;
                break;

            }
            else if (donor.get(i).getGau() == 0 && (totalgau > 50)) {
                //i forget when this is geeting called
                System.out.println("rare condition is getting called");
                result.get(i).setGau(result.get(i).getAnn());
                donor.get(i).setGau(result.get(i).getAnn());
                result.get(i).setAnn(0);
                donor.get(i).setAnn(0);
                totalgau -= result.get(i).getGau();

            }
        }

        for(int i=gauDonor;i<=nGauDonor;i++)
        {
            double temp = 0;
            int devanaKg;
            int tmg = 0, tma = 0;

            if (i > 0) {
                tempMonyGau = donor.get(i).getGau() + ddg.get(i - 1);
                ddg.set(i-1,0.0);
            } else {
                tempMonyGau = donor.get(i).getGau();
            }

            while (tempMonyGau > prizePerKg.get(j) && j < gauSaman) {
                temp = Math.round(tempMonyGau / prizePerKg.get(j));
                if (temp > tempKg) {

                    devanaKg = tempKg;
                    tmg += prizePerKg.get(j) * devanaKg;

                    total.set(j, total.get(j) + devanaKg);

                    ddg.set(i, ddg.get(i) - (devanaKg * prizePerKg.get(j)));
                    //System.out.print(saman[j]+":"+devanaKg+"kg ");
                    result.get(i).getDataG().add(new Data(saman.get(j).getName(), devanaKg));

                    tempKg -= devanaKg;
                    tempMonyGau -= (prizePerKg.get(j) * devanaKg);
                    totalgau -= prizePerKg.get(j) * devanaKg;
                    j++;
                    tempKg = saman.get(j).getKg();

                }
                else {

                    devanaKg = (int) temp;
                    total.set(j, total.get(j) + devanaKg);
                    tmg += prizePerKg.get(j) * devanaKg;

                    ddg.set(i, ddg.get(i) - (devanaKg * prizePerKg.get(j)));

                    result.get(i).getDataG().add(new Data(saman.get(j).getName(), devanaKg));
                    tempKg -= devanaKg;
                    tempMonyGau -= prizePerKg.get(j) * devanaKg;
                    totalgau -= prizePerKg.get(j) * devanaKg;

                }
            }
            if (i < donor.size() - 1) {
                ddg.set(i + 1, ddg.get(i) + ddg.get(i + 1));
            }
        }
        for(Result i : result)
        {
            System.out.println(i.toString());
        }
        System.out.println("\n\n\n\n");

//        for (donorData i : donor)
//        {
//            System.out.println(i.toString());
//        }
        donor.sort(Comparator.comparingDouble(donorData::getAnn));
        donor = donor.reversed();

        result.sort(Comparator.comparingDouble(Result::getAnn));
        result = result.reversed();

//        for (donorData i : donor)
//        {
//            System.out.println(i.toString());
//        }
//
//        System.out.println("\n\n\n\n\n 90909090909009\n\n\n\n\n\n");
//
//        for(samanData i:saman)
//        {
//            System.out.println(i.toString());
//        }



        for (donorData s : donor) {
            dda.add((double) s.getAnn());
            //System.out.println("before dda : " + s.getAnn());
        }
////////////////////////////////////////////////
        for (int i = 0; i < donor.size(); i++) {
            int tmg = 0, tma = 0;
            if (i > 0) {
                tempMonyAnn = donor.get(i).getAnn() + dda.get(i - 1);
                dda.set(i-1, 0.0);
            } else {
                tempMonyAnn = donor.get(i).getAnn();
            }
            double temp = 0;
            int devanaKg;



            while (tempMonyAnn > prizePerKg.get(k) && k < saman.size()) {
                temp = Math.round(tempMonyAnn / prizePerKg.get(k));
                if (temp > tempKgA) {

                    devanaKg = tempKgA;
                    tma += prizePerKg.get(k) * devanaKg;

                    total.set(k, total.get(k) + devanaKg);
                    dda.set(i, dda.get(i) - (devanaKg * prizePerKg.get(k)));

                    result.get(i).getDataA().add(new Data(saman.get(k).getName(), devanaKg));

                    tempKgA -= devanaKg;
                    tempMonyAnn -= (prizePerKg.get(k) * devanaKg);

                    if (k < saman.size() - 1) {
                        k++;
                    }

                    tempKgA = saman.get(k).getKg();

                } else {

                    devanaKg = (int) temp;
                    total.set(k, total.get(k) + devanaKg);
                    tma += prizePerKg.get(k) * devanaKg;

                    dda.set(i, dda.get(i) - (devanaKg * prizePerKg.get(k)));

                    result.get(i).getDataA().add(new Data(saman.get(k).getName(), devanaKg));
                    tempKgA -= devanaKg;
                    tempMonyAnn -= prizePerKg.get(k) * devanaKg;

                }
            }
            y += tma;
            if (i < donor.size() - 1) {
                dda.set(i + 1, dda.get(i) + dda.get(i + 1));
            }
        }
////////////////////////////////////////////////

        for(Result jj : result)
        {
            System.out.println(jj.toString());
        }
        System.out.println("\n\n\n\n");


        for(Double d : ddg)
        {
            System.out.println("ddg : "+d);
        }



        result.sort(Comparator.comparingDouble(Result::getGau).thenComparing(Result::getAnn));

        return result.reversed();
    }
}