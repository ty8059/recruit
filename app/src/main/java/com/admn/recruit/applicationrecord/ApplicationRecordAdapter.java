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
        viewHolder.positionName.setText(record.getPositionName());
        viewHolder.positionWorkType.setText(record.getPositionWorkType());
        viewHolder.positionIsPassed.setText(record.getPositionIsPassed());


        return convertView;
    }

    class ViewHolder{
        TextView positionName;
        TextView positionSalary;
        TextView positionWorkType;
        TextView positionIsPassed;

    }
}
