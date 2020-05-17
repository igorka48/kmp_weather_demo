//
//  WeatherViewController.swift
//  KMP Weather Demo
//
//  Created by Igor on 06.05.2020.
//  Copyright © 2020 OwlsDevelopers. All rights reserved.
//

import UIKit
import SharedCode

class WeatherViewController: UIViewController {

    private var viewModel: WeatherViewModel!
    
    // View
    @IBOutlet weak var cityLabel: UILabel!
    // View
    @IBOutlet weak var dscLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

       viewModel = WeatherViewModel()
        viewModel.weatherLiveData.addObserver { (value) in
            if(value is Weather){
            let weather: Weather = value as! Weather
                self.cityLabel.text = weather.name
                self.dscLabel.text = weather.weather?.first?.description
            }
        }
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

    deinit {
           viewModel.onCleared()
       }
}
