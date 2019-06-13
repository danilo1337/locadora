package util.pdf;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class PdfTemplate {
	private String nome_pdf = "";
	private String caminho = "";
	public abstract PdfPTable gerarTabela() throws SQLException;
	
	public PdfTemplate(String nome_pdf) {
		this.nome_pdf = nome_pdf;
		this.caminho = System.getenv("APPDATA")+"\\pdf_gerado_PI\\"+getNome_pdf()+".pdf";;
	}
	
	public void gerarPersonalizado(String msgTitulo,String msgParagrafo) throws Exception {
		Document doc = new Document(PageSize.A4);
		criarDiretorio();
		try {
//-----------------------------Titulo-----------------------------
			PdfWriter.getInstance(doc, new FileOutputStream(caminho));
			doc.open();
			Paragraph titulo = new Paragraph(msgTitulo);
			titulo.setAlignment(1);
			doc.add(titulo);
			titulo = new Paragraph("");
			doc.add(titulo);
//-----------------------------Config de dadosSocios-----------------------------
			String paragrafo_1 = "";
			paragrafo_1 += "\t"+msgParagrafo;
			Chunk ch = new Chunk(paragrafo_1);
			doc.add(ch);
//-----------------------------Gera tabela-----------------------------
			PdfPTable tabela = gerarTabela();
			doc.add(tabela);
//-----------------------------Data-----------------------
			Locale localeBR = new Locale("pt", "BR");
			SimpleDateFormat fmt = new SimpleDateFormat("'Goiânia,' dd 'de' MMMM 'de' yyyy '\n' hh:mm:ss", localeBR);
			String msg = fmt.format(new Date());
			Paragraph rodape = new Paragraph(msg);
			rodape.setAlignment(1);
			doc.add(rodape);
//-----------------------------Fecha o documento e abri o Pdf gerado--------
			doc.close();
			Desktop.getDesktop().open(new File(caminho));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private static void criarDiretorio() throws Exception {
		try {
			File diretorio = new File(System.getenv("APPDATA") + "\\pdf_gerado_PI");
			diretorio.mkdir();
		} catch (Exception ex) {
			throw ex;

		}
	}

	public String getNome_pdf() {return nome_pdf;}
	public void setNome_pdf(String nome_pdf) {this.nome_pdf = nome_pdf;}
	public String getCaminho() {return caminho;}
	public void setCaminho(String caminho) {this.caminho = caminho;}
}
