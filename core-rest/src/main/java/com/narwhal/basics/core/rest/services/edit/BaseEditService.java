package com.narwhal.basics.core.rest.services.edit;

import com.narwhal.basics.core.rest.model.BaseModel;
import com.narwhal.basics.core.rest.model.BaseModelUpdatable;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.collections.BaseModelMapUtils;

import java.io.Serializable;
import java.util.*;

public abstract class BaseEditService<C extends Serializable, T extends BaseModelUpdatable, T2 extends BaseModel> {

    public List<T> editModels(C config, final List<T2> modelDTOS) {
        ApiPreconditions.checkNotNull(modelDTOS, "modelDTOS");
        //
        Map<String, T> storedUpdatables = BaseModelMapUtils.buildMapFromList(getUpdatables(config));
        Map<String, T2> dtos = BaseModelMapUtils.buildMapFromList(modelDTOS);
        //
        List<T> modifiedUpdatables = new ArrayList<>();
        //
        List<T> toDeleteUpdatables = new ArrayList<>();
        //
        List<T> totalUpdatables = new ArrayList<>();
        //
        // Check if some stored project was deleted
        for (T stored : storedUpdatables.values()) {
            //
            if (dtos.containsKey(stored.getId())) {
                T2 dto = dtos.get(stored.getId());
                //still exists
                if (stored.requiresUpdate(dto)) {
                    // Exist. Update them
                    stored.update(dto);
                    modifiedUpdatables.add(stored);
                }
                //
                totalUpdatables.add(stored);
            } else {
                // Add to deletion list
                toDeleteUpdatables.add(stored);
            }
        }
        //
        // Check if some dtos are new items
        for (T2 dto : modelDTOS) {
            if (!storedUpdatables.containsKey(dto.getId())) {
                T model = initFromDTO(config, dto);
                modifiedUpdatables.add(model);
                totalUpdatables.add(model);
            }
        }
        //
        // Delete them all from db and gcs
        Collections.sort(toDeleteUpdatables, new UpdatableModelComparator<T>());
        deleteEntities(config, toDeleteUpdatables);
        //
        // Save all the modified stuff
        Collections.sort(modifiedUpdatables, new UpdatableModelComparator<T>());
        saveAllEntities(modifiedUpdatables);
        //
        ArrayList<T> result = new ArrayList<>(totalUpdatables);
        Collections.sort(result, new UpdatableModelComparator<T>());
        //
        return result;
    }

    protected abstract T initFromDTO(C config, T2 dto);

    protected abstract List<T> getUpdatables(C config);

    protected abstract void saveAllEntities(List<T> modifiedUpdatables);

    protected abstract void deleteEntities(C editDTO, List<T> toDeleteUpdatables);

    private class UpdatableModelComparator<T extends BaseModelUpdatable> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return new Integer(o1.getSort()).compareTo(o2.getSort());
        }
    }
}