package util.pdf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import entidade.Filmes;

public class PdfFilmes extends PdfTemplate{
	String tituloCelulas[] = null;
	PdfPTable tabela = null;
	List<PdfPCell>celula = null;
	List<?> listaDeObjetos = null;
	public PdfFilmes(String nome_pdf,String tituloCelulas[],List<?> listaDeObjetos) {
		super(nome_pdf);
		this.tituloCelulas = tituloCelulas;
		this.tabela = new PdfPTable(tituloCelulas.length);
		this.celula = new ArrayList<>();
		this.listaDeObjetos = listaDeObjetos;
	}

	@Override
	public PdfPTable gerarTabela() throws SQLException {
		tabela.setWidthPercentage(100);
		tabela.setSpacingAfter(10f);
		tabela.setSpacingBefore(10f);
		//coloca o nome das celulas
		for (String nomes : tituloCelulas) {
			Paragraph p = new Paragraph(nomes,FontFactory.getFont(FontFactory.TIMES_BOLD, 9,BaseColor.BLUE));
			PdfPCell c = new PdfPCell(p);
			c.setHorizontalAlignment(Element.ALIGN_CENTER);
			celula.add(c);
		}
		//coloca os valores nas celulas
		for (Object o:listaDeObjetos ) {
			Filmes p = (Filmes) o; 
			celula.add(new PdfPCell(new Paragraph(p.getId()+"",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
			celula.add(new PdfPCell(new Paragraph(p.getTitulo(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
			celula.add(new PdfPCell(new Paragraph(p.getGenero(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
			celula.add(new PdfPCell(new Paragraph(p.getFaixaEtaria(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
			celula.add(new PdfPCell(new Paragraph(p.getAnoLancamento(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
		}
		//adiciona as celulas na tabela
		for (int i = 0; i < celula.size(); i++) {
			tabela.addCell(celula.get(i));
		}
		return tabela;
	}


}
