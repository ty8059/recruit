package com.admn.recruit.applicationrecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.admn.recruit.R;
import com.admn.recruit.common.Constants;

import java.util.List;

public class ApplicationRecordAdapter extends ArrayAdapter<ApplicationRecord> {

    public ApplicationRecordAdapter(Context context, int resource, List<ApplicationRecord> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApplicationRecord record = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.application_record_item,null);

            viewHolder = new ViewHolder();

            viewHolder.positionName = convertView.findViewById(R.id.tv_position_name);
            viewHolder.positionSalary = convertView.findViewById(R.id.tv_position_salary);
            viewHolder.positionWorkType = convertView.findViewById(R.id.tv_position_work_type);
            viewHolder.positionIsPassed = convertView.findViewById(R.id.tv_position_is_passed);

            convertView.setTag(viewHolder);

        } else{
            viewHolder  = (ViewHolder) convertView.getTag();
        }


        // 设置textview
        if (record.getSalaryType().equals(Constants.SALARY_TYPE.MONTH)) {
            viewHolder.positionSalary.setText(String.valueOf(record.getPositionSalary()) + " / 月");
        } else {
            viewHolder.positionSalary.setText(String.valueOf(record.getPositionSalary()) + " / 日");
        }


        if (record.getPositionIsPassed().equals(Constants.AUDITING_STATE.PASSED)) {
            viewHolder.positionIsPassed.setText("已通过");
        }
        else if (record.getPositionIsPassed().equals(Constants.AUDITING_STATE.FAILED)) {
            viewHolder.positionIsPassed.setText("未通过");
        }
        else {
            viewHolder.positionIsPassed.setText("待审核");
        }


        if (record.getPositionWorkType().equals(Constants.WORK_TYPE.FULL_TIME)) {
            viewHolder.positionWorkType.setText("全职");
        }
        else if (record.getPositionWorkType().equals(Constants.WORK_TYPE.PART_TIME)) {
            viewHolder.positionWorkType.setText("兼职");
        }
        else {
            viewHolder.positionWorkType.setText("实习");
        }

        viewHolder.positionName.setText(record.getPositionName());


        return convertView;
    }

    class ViewHolder{
        TextView positionName;
        TextView positionSalary;
        TextView positionWorkType;
        TextView positionIsPassed;

    }
}
