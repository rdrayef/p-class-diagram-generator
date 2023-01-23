package org.mql.java.enums;

public enum RelationType {
		DEPENDENCY("..>"),
		ASSOCIATION("--"),
		AGGREGATION("--o"),
		COMPOSITION("--*"),
		GENERALIZATION("--|>"),
		REALIZATION("..|>"),
		; 

		private String symbol;

		
		private RelationType(String symbol) {
			this.symbol = symbol;
		}

		
		public String getSymbol(){
			return this.symbol;
		}
}
