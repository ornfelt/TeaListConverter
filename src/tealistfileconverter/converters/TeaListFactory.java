package tealistfileconverter.converters;

import tealistfileconverter.FileFormat;

/**
 *
 * @author jonas
 */
    public class TeaListFactory { //simple TeaListFactory responsible for creating the correct converter 

    public TeaListConverter createConverter(String type) {
        TeaListConverter converter = null;

        if (FileFormat.CSV.equals(type)) {
            converter = new TeaListConverterCsv();
        } else if (FileFormat.DSV.equals(type)) {
            converter = new TeaListConverterDsv();
        } else if (FileFormat.XML.equals(type)) {
            converter = new TeaListConverterXml();
        }
        return converter;
    }
}
