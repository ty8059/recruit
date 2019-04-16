package com.admn.recruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.admn.recruit.R;
import com.admn.recruit.common.Constants;
import com.admn.recruit.model.PositionApp;

import java.util.List;

public class PositionAppAdapter extends ArrayAdapter<PositionApp> {

    public PositionAppAdapter(Context context, int resource, List<PositionApp> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PositionApp positionApp = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.position_app_item,null);

            viewHolder = new ViewHolder();

            viewHolder.positionName = convertView.findViewById(R.id.tv_position_name);
            viewHolder.salary = convertView.findViewById(R.id.tv_position_salary);
            viewHolder.workType = convertView.findViewById(R.id.tv_position_work_type);
            viewHolder.status = convertView.findViewById(R.id.tv_position_is_passed);

            convertView.setTag(viewHolder);

        } else{
            viewHolder  = (ViewHolder) convertView.getTag();
        }


        // 设置textView
        if (positionApp.getSalaryType().equals(Constants.SALARY_TYPE.MONTH)) {
            viewHolder.salary.setText(String.valueOf(positionApp.getSalary()) + " / 月");
        } else {
            viewHolder.salary.setText(String.valueOf(positionApp.getSalary()) + " / 日");
        }


        if (positionApp.getStatus().equals(Constants.AUDITING_STATE.PASSED)) {
            viewHolder.status.setText("已通过");
        }
        else if (positionApp.getStatus().equals(Constants.AUDITING_STATE.FAILED)) {
            viewHolder.status.setText("未通过");
        }
        else {
            viewHolder.status.setText("待审核");
        }


        if (positionApp.getWorkType().equals(Constants.WORK_TYPE.FULL_TIME)) {
            viewHolder.workType.setText("全职");
        }
        else if (positionApp.getWorkType().equals(Constants.WORK_TYPE.PART_TIME)) {
            viewHolder.workType.setText("兼职");
        }
        else {
            viewHolder.workType.setText("实习");
        }

        viewHolder.positionName.setText(positionApp.getPositionName());

        return convertView;
    }

    class ViewHolder{
        TextView positionName;
        TextView salary;
        TextView workType;
        TextView status;
    }
}
