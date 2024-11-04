package com.naveed.chainofresponsibilityatmexample;

import java.util.Scanner;

public class ChainOfRespATMExample {
	DispenceChain d;
	
	ChainOfRespATMExample () {
		this.d = new Notes2000Rack();
		DispenceChain d1 = new Notes500Rack();
		DispenceChain d2 = new Notes100Rack();
		d.chain(d1);
		d1.chain(d2);
	}

	public static void main(String[] args) {
		ChainOfRespATMExample c = new ChainOfRespATMExample();
		
		while(true) {
			System.out.println("Please enter amount want to be withdraw");
			Scanner s = new Scanner(System.in);
			int amount = s.nextInt();			
			if(amount != 0) {
				
				if(amount % 100 != 0) {
					System.out.println("Please enter multiples of 100");
					return;
				}
				else {
					c.d.dispence(new Currency(amount));
				}
			}
		}
	}
}

class Currency{
	private int amount;

	public Currency(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
		
}

interface DispenceChain {
	void dispence(Currency currency);
	void chain(DispenceChain nextChain);
}

class Notes2000Rack implements DispenceChain{
	
	DispenceChain chain;

	@Override
	public void dispence(Currency currency) {
		int noOf2000Notes = (int)currency.getAmount()/2000;
		System.out.println("Dispencing "+noOf2000Notes + " 2000 notes");
		
		int reminder = (int)currency.getAmount()%2000;
		
		if(reminder != 0) {
			this.chain.dispence(new Currency(reminder));
		}
	}

	@Override
	public void chain(DispenceChain chain) {
		this.chain = chain;
	}
	
}

class Notes500Rack implements DispenceChain{
	
	DispenceChain chain;

	@Override
	public void dispence(Currency currency) {
		int noOf1000Notes = (int)currency.getAmount()/500;
		System.out.println("Dispencing "+noOf1000Notes + " 500 notes");
		int reminder = (int)currency.getAmount()%500;
		
		if(reminder != 0) {
			chain.dispence(new Currency(reminder));
		}
	}

	@Override
	public void chain(DispenceChain chain) {
		this.chain = chain;
	}
}

class Notes100Rack implements DispenceChain{
	
	DispenceChain chain;

	@Override
	public void dispence(Currency currency) {
		int noOf1000Notes = (int)currency.getAmount()/100;
		System.out.println("Dispencing "+noOf1000Notes + " 100 notes");
		int reminder = (int)currency.getAmount()%100;
		
		if(reminder != 0) {
			chain.dispence(new Currency(reminder));
		}
	}

	@Override
	public void chain(DispenceChain chain) {
		this.chain = chain;
	}
	
}
