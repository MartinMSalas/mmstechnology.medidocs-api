package com.medi.docs.medidocsapi.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import com.medi.docs.medidocsapi.exception.PdfGenerationException;
import com.medi.docs.medidocsapi.model.RecipeResponse;

import java.io.ByteArrayOutputStream;

/**
 * Clase de utilidad para generar documentos PDF de recetas médicas.
 */
public final class PdfGenerator {

    private static final Font TITLE_FONT = new Font(Font.HELVETICA, 18, Font.BOLD);
    private static final Font SUBTITLE_FONT = new Font(Font.HELVETICA, 14, Font.BOLD);
    private static final Font TEXT_FONT = new Font(Font.HELVETICA, 12);
    private static final Font SMALL_FONT = new Font(Font.HELVETICA, 10);

    private PdfGenerator() {
        // Utility class - no instances allowed
    }

    /**
     * Genera un documento PDF para la receta proporcionada.
     *
     * @param recipe los datos de la receta
     * @return el contenido del PDF en un arreglo de bytes
     * @throws PdfGenerationException si ocurre un error durante la generación del PDF
     */
    public static byte[] generateRecipePdf(RecipeResponse recipe) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50); // Margen izquierdo, derecho, arriba, abajo
            PdfWriter.getInstance(document, baos);
            document.open();

            addHeader(document);
            addPatientInfo(document, recipe);
            addPrescription(document, recipe);
            addFooter(document, recipe);

            document.close();
            return baos.toByteArray();
        } catch (Exception ex) {
            throw new PdfGenerationException("No se pudo generar el documento PDF", ex);
        }
    }

    private static void addHeader(Document document) throws DocumentException {
        Paragraph clinicName = new Paragraph("CENTRO DE SALUD FRANCISCO", TITLE_FONT);
        clinicName.setAlignment(Element.ALIGN_CENTER);
        document.add(clinicName);

        Paragraph clinicAddress = new Paragraph("Barrio La Bodega - Av. Fray Mamerto Esquiú", SMALL_FONT);
        clinicAddress.setAlignment(Element.ALIGN_CENTER);
        document.add(clinicAddress);

        Paragraph phone = new Paragraph("Tel: 3837-691723", SMALL_FONT);
        phone.setAlignment(Element.ALIGN_CENTER);
        document.add(phone);

        document.add(Chunk.NEWLINE);
        document.add(new LineSeparator());
        document.add(Chunk.NEWLINE);
    }

    private static void addPatientInfo(Document document, RecipeResponse recipe) throws DocumentException {
        Paragraph title = new Paragraph("Datos del Paciente", SUBTITLE_FONT);
        title.setAlignment(Element.ALIGN_LEFT);
        document.add(title);

        document.add(Chunk.NEWLINE);

        document.add(new Paragraph("Nombre completo: " + recipe.fullName(), TEXT_FONT));
        document.add(new Paragraph("DNI: " + recipe.dni(), TEXT_FONT));
        document.add(new Paragraph("Edad: " + (recipe.age() != null ? recipe.age() + " años" : "No informado"), TEXT_FONT));
        document.add(new Paragraph("Obra social: " + recipe.healthInsurance(), TEXT_FONT));
        document.add(new Paragraph("Número de afiliado: " + recipe.affiliateNumber(), TEXT_FONT));
        if (recipe.plan() != null) {
            document.add(new Paragraph("Plan: " + recipe.plan(), TEXT_FONT));
        }
        document.add(new Paragraph("Ciudad: " + recipe.city(), TEXT_FONT));
        document.add(new Paragraph("Fecha de emisión: " + recipe.date(), TEXT_FONT));

        document.add(Chunk.NEWLINE);
        document.add(new LineSeparator());
        document.add(Chunk.NEWLINE);
    }

    private static void addPrescription(Document document, RecipeResponse recipe) throws DocumentException {
        Paragraph title = new Paragraph("Prescripción Médica", SUBTITLE_FONT);
        title.setAlignment(Element.ALIGN_LEFT);
        document.add(title);

        document.add(Chunk.NEWLINE);

        document.add(new Paragraph("📌 Medicamento 1: " + recipe.rp1Medication(), TEXT_FONT));
        document.add(new Paragraph("🩺 Diagnóstico 1: " + recipe.rp1Diagnosis(), TEXT_FONT));

        if (recipe.rp2Medication() != null && recipe.rp2Diagnosis() != null) {
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("📌 Medicamento 2: " + recipe.rp2Medication(), TEXT_FONT));
            document.add(new Paragraph("🩺 Diagnóstico 2: " + recipe.rp2Diagnosis(), TEXT_FONT));
        }

        document.add(Chunk.NEWLINE);
        document.add(new LineSeparator());
        document.add(Chunk.NEWLINE);
    }

    private static void addFooter(Document document, RecipeResponse recipe) throws DocumentException {
        Paragraph doctorInfo = new Paragraph("Médico Responsable", SUBTITLE_FONT);
        doctorInfo.setAlignment(Element.ALIGN_LEFT);
        document.add(doctorInfo);

        document.add(Chunk.NEWLINE);

        document.add(new Paragraph("Nombre: " + recipe.doctorName(), TEXT_FONT));
        document.add(new Paragraph("Matrícula: " + recipe.doctorLicenseNumber(), TEXT_FONT));

        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("Firma: ____________________________________", TEXT_FONT));
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        Paragraph footer = new Paragraph("Documento generado por MediDocs API", SMALL_FONT);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);
    }
}
