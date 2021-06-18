package com.asi.unicomgroupsurvey.Views.sketch;

import com.asi.unicomgroupsurvey.baseClass.BaseView;
import com.asi.unicomgroupsurvey.data.models.jops.Job;
import com.asi.unicomgroupsurvey.data.models.positions.PositionResponseDetails;
import com.asi.unicomgroupsurvey.data.models.sketch.Sketch;

import java.util.ArrayList;
import java.util.List;

public interface SketchView extends BaseView {
    void startOutDoorActivity();
    void afterAdd(List<Sketch> sketches);
    void afterDelete(List<Sketch> sketches);
}
