package com.github.zxh.classpy.bitcoin.types;

import com.github.zxh.classpy.bitcoin.BlockPart;
import com.github.zxh.classpy.bitcoin.BlockReader;

import java.util.function.Supplier;

public class Table extends BlockPart {

    private Supplier<? extends BlockPart> supplier;
    private String componentName;

    public Table(Supplier<? extends BlockPart> supplier) {
        this.supplier = supplier;
        componentName = supplier.get().getClass().getSimpleName();
    }

    @Override
    protected void readContent(BlockReader reader) {
        long count = readVarInt(reader, "Count");
        for (long i = 0; i < count; i++) {
            BlockPart element = supplier.get();
            add(componentName + "#" + i, element);
            element.read(reader);
        }
        setDesc(Long.toString(count));
    }

}
